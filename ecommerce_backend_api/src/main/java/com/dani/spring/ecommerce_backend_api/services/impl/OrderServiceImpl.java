package com.dani.spring.ecommerce_backend_api.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.dto.requests.OrderPaymentRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.OrderRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.addresses.Address;
import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;
import com.dani.spring.ecommerce_backend_api.entities.cart.CartItem;
import com.dani.spring.ecommerce_backend_api.entities.order.Order;
import com.dani.spring.ecommerce_backend_api.entities.order.OrderItem;
import com.dani.spring.ecommerce_backend_api.entities.order.OrderPayment;
import com.dani.spring.ecommerce_backend_api.entities.payment_method.PaymentMethod;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.repositories.CartRepository;
import com.dani.spring.ecommerce_backend_api.repositories.OrderItemRepository;
import com.dani.spring.ecommerce_backend_api.repositories.OrderPaymentRepository;
import com.dani.spring.ecommerce_backend_api.repositories.OrderRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.OrderService;
import com.dani.spring.ecommerce_backend_api.services.ProductService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService{

    private final String INITIAL_ORDER_STATUS = "paid";

    @Autowired
    OrderRepository repository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderPaymentRepository orderPaymentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Transactional(readOnly=true)
    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    
    @Override
    public List<Order> getAllMyOrders(String username) {
        User user = getUserByUsername(username);
        return repository.findByUser(user);
    }

    @Transactional(readOnly=true)
    @Override
    public Order getOrderById(Long orderId, String username) {
        User user = getUserByUsername(username);
        Order order = repository.findByIdAndUserOrderByCreatedAtDesc(orderId, user);
        //Si el usuario no tiene este order devolvemos un 404
        if (order == null){
            throw new EntityNotFoundException("No se ha encontrado el pedido indicado");
        }

        return order;
    }

    @Transactional(readOnly=true)
    @Override
    public Order getOrderByIdAdmin(Long orderId){
        Optional<Order> optOrder = repository.findById(orderId);
        if (!optOrder.isPresent()){
            throw new EntityNotFoundException("No se ha encontrado el pedido con id: " + orderId);
        }

        return optOrder.orElseThrow();
    }

    @Transactional(readOnly=true)
    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Transactional
    @Override
    public Order createOrder(User user, Cart cart, Address address, List<PaymentMethod> paymentMethods, OrderRequestDto request) {
        //Montamos la direccion de envio
        String fullShippingAddress = getFullShippingAddress(address);
        //Creamos el pedido
        Order newOrder = new Order(user, cart.getAmount(), INITIAL_ORDER_STATUS, fullShippingAddress, address.getCountry(), address.getCity(), address.getPostalCode(), null, null);
        Order order = repository.save(newOrder);
        //Guardamos los productos comprados
        List<OrderItem> orderItems= orderItemRepository.saveAll(getOrderItems(order, cart.getItems()));
        //Guardamos los metodos de pago utilizados
        List<OrderPayment> OrderPayments = orderPaymentRepository.saveAll(getOrderPayments(order, paymentMethods, request));

        //Restamos stock de los productos
        discountProductsStock(orderItems);

        //Vaciamos el carrito del usuario (orphanRemoval lo elimina)
        cart.getItems().clear();

        //Seteamos valores al order y devolvemos
        order.setItems(orderItems);
        order.setPayments(OrderPayments);

        return order;
    }

    /**
     * Metodo que convierte una lista de productos del carrito en una lista de productos del pedido
     * @param order
     * @param cartItems
     * @return
     */
    private List<OrderItem> getOrderItems(Order order, List<CartItem> cartItems){
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems){
            OrderItem orderItem = new OrderItem(
                order,
                cartItem.getProduct(),
                cartItem.getQuantity(),
                cartItem.getProduct().getPrice()
            );
            orderItems.add(orderItem);
        }

        return orderItems;
    }

    /**
     * Devuelve una lista de pagos del pedido atraves de una lista de metodos de pago y OrderRequestDto
     * @param order
     * @param paymentMethods
     * @param request
     * @return
     */
    private List<OrderPayment> getOrderPayments(Order order, List<PaymentMethod> paymentMethods, OrderRequestDto request){
        List<OrderPayment> orderPayments = new ArrayList<>();
        for (PaymentMethod paymentMethod : paymentMethods){
            OrderPayment orderPayment = new OrderPayment(
                order,
                paymentMethod,
                getPaymentAmount(paymentMethod, request)
            );
            orderPayments.add(orderPayment);
        }

        return orderPayments;
    }

    /**
     * Devuelve la cantidad de dinero aportada por el metodo de pago
     * @param paymentMethod
     * @param request
     * @return
     */
    private BigDecimal getPaymentAmount(PaymentMethod paymentMethod, OrderRequestDto request){
        BigDecimal result = BigDecimal.ZERO;
        for (OrderPaymentRequestDto orderPaymentRq : request.getPayments()){
            if (orderPaymentRq.getPaymentId().equals(paymentMethod.getId())){
                result = orderPaymentRq.getAmount();
                break;
            }
        }

        return result;
    }


    /**
     * Descuenta el stock comprado de los productos
     * @param orderItems
     */
    private void discountProductsStock(List<OrderItem> orderItems){
        for (OrderItem orderItem : orderItems){
            productService.discountStock(orderItem.getProduct().getId(), orderItem.getQuantity());
        }
    }

    /**
     * Devuelve la direccion de envio concatenada
     * @param address
     * @return
     */
    private String getFullShippingAddress(Address address){
        StringBuilder fullShippingAddress = new StringBuilder();
        fullShippingAddress.append(address.getStreet());
        fullShippingAddress.append(", ");
        fullShippingAddress.append(address.getNumber());
        fullShippingAddress.append(", ");
        fullShippingAddress.append(address.getFloor());
        fullShippingAddress.append(" - ");
        fullShippingAddress.append(address.getDoor());

        return fullShippingAddress.toString();
    }

}
