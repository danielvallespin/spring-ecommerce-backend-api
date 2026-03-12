package com.dani.spring.ecommerce_backend_api.controllers;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.requests.OrderPaymentRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.OrderRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.OrderDetailRespondeDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.OrderResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.addresses.Address;
import com.dani.spring.ecommerce_backend_api.entities.cart.Cart;
import com.dani.spring.ecommerce_backend_api.entities.cart.CartItem;
import com.dani.spring.ecommerce_backend_api.entities.order.Order;
import com.dani.spring.ecommerce_backend_api.entities.payment_method.PaymentMethod;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.exceptions.BadRequestException;
import com.dani.spring.ecommerce_backend_api.services.AddressService;
import com.dani.spring.ecommerce_backend_api.services.CartService;
import com.dani.spring.ecommerce_backend_api.services.OrderService;
import com.dani.spring.ecommerce_backend_api.services.PaymentMethodService;
import com.dani.spring.ecommerce_backend_api.services.ProductService;
import com.dani.spring.ecommerce_backend_api.services.UserService;
import com.dani.spring.ecommerce_backend_api.utils.OrderUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "9. Pedidos", description = "API para gestión de pedidos")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    PaymentMethodService paymentMethodService;

    @Autowired
    ProductService productService;

    @Autowired
    AddressService addressService;

    //GET_ALL
    @Operation(summary = "Obtener todos los pedidos (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pedidos obtenidos correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class))))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(){
        return ResponseEntity.ok(OrderUtility.getOrderResponseList(service.getAllOrders()));
    }

    //GET_ONE_ADMIN
    @Operation(summary = "Obtener detalle de pedido (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Detalle del pedido correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailRespondeDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el pedido indicado", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailRespondeDto> getOrderDetailAdmin(@PathVariable Long orderId, Principal principal){
        //Obtenemos el pedido (si no exite devuelve un 404)
        Order order = service.getOrderByIdAdmin(orderId);
        return ResponseEntity.ok(OrderUtility.getOrderDetailResponse(order));
    }

    //GET_MY_ALL
    @Operation(summary = "Obtener mis pedidos")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Pedidos obtenidos correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class))))
    })
    @GetMapping("/my/all")
    public ResponseEntity<List<OrderResponseDto>> getAllMyOrders(Principal principal){
        return ResponseEntity.ok(OrderUtility.getOrderResponseList(service.getAllMyOrders(principal.getName())));
    }

    //GET_MY_ONE
    @Operation(summary = "Obtener detalle de mi pedido")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Detalle del pedido correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailRespondeDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el pedido indicado", content = @Content)
    })
    @GetMapping("/my/{orderId}")
    public ResponseEntity<OrderDetailRespondeDto> getOrderDetail(@PathVariable Long orderId, Principal principal){
        //Obtenemos el pedido (si no exite devuelve un 404)
        Order order = service.getOrderById(orderId, principal.getName());
        return ResponseEntity.ok(OrderUtility.getOrderDetailResponse(order));
    }

    //CREATE
    @Operation(
        summary = "Crear un pedido",
        description = "Esta accion genera un pedido, elimina todos los producto del carrito y descuenta el stock de los productos"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Detalle del pedido correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailRespondeDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos de carrito o metodos de pago invalidos", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<OrderDetailRespondeDto> createOrder(@Valid @RequestBody OrderRequestDto request, Principal principal){
        //--VALIDACIONES--
        //Obtenemos el usuario
        User user = userService.getUserByUsername(principal.getName());
        //Obtenemos el  carrito del usuario
        Cart cart = cartService.getUserCart(user);
        //Validamos que el carrito tenga productos
        if (cart.getItems() == null || cart.getItems().isEmpty()){
            throw new BadRequestException("El carrito esta vacio");
        }

        //Obtenemos la direccion de envio indicada (si no existe devuelve un 404)
        Address address = addressService.getUserAddressById(request.getAddressId(), user);

        //Validamos que no se supere el stock de ningun producto
        Product productOutOfStock = validateProductsStock(cart.getItems());
        if (productOutOfStock != null){
            throw new BadRequestException(String.format("EL producto %s supera el stock permitido: %d", productOutOfStock.getName(), productOutOfStock.getStock()));
        }

        //Validamos que el precio final coincida con el introducido en los metodos de pago
        BigDecimal methodsAmount = getMethodsAmount(request.getPayments());
        if (!methodsAmount.equals(cart.getAmount())){
            throw new BadRequestException("La cantidad a pagar no es la correcta");
        }

        //Obtenemos los ids de los pagos selecionados
        List<Long> paymentsIds = getOrderPaymentsIds(request.getPayments());
        //Obtenemos los metodos de pago de la db
        List<PaymentMethod> paymentMethods = paymentMethodService.getUserPaymentMethodsByIds(user, paymentsIds);
        //Validamos que todos los metodos indicados existan y esten habilitados
        boolean isAnyMethodDisabled = validateEnabledMethods(paymentMethods);
        if (isAnyMethodDisabled || paymentMethods.isEmpty() || paymentMethods.size() != paymentsIds.size()){
            throw new BadRequestException("Los metodos de pago no son validos");
        }

        //--CREACION--
        Order newOrder = service.createOrder(user, cart, address, paymentMethods, request);

        return ResponseEntity.ok(OrderUtility.getOrderDetailResponse(newOrder));
    }


    /**
     * Metodo que convierte los payments del request en una lista de Long paymentId 
     * @param orderPayments
     * @return
     */
    private List<Long> getOrderPaymentsIds(List<OrderPaymentRequestDto> orderPayments){
        List<Long> paymentsIds = new ArrayList<>();
        if (orderPayments != null && !orderPayments.isEmpty()){
            for(OrderPaymentRequestDto orderPayment : orderPayments){
                paymentsIds.add(orderPayment.getPaymentId());
            }
        }

        return paymentsIds;
    }

    /**
     * Metodo que comprueba que todos los metodos de pago introducidos esten habilitados
     * @param paymentMethods
     * @return
     */
    private boolean validateEnabledMethods(List<PaymentMethod> paymentMethods){
        boolean isAnyMethodDisabled = false;
        for (PaymentMethod payment : paymentMethods){
            if (!payment.isEnabled()){
                isAnyMethodDisabled = true;
                break;
            }
        }

        return isAnyMethodDisabled;
    }

    /**
     * Obtener la cantidad total de pago de los metodos introducidos
     * @param orderPayments
     * @return
     */
    private BigDecimal getMethodsAmount(List<OrderPaymentRequestDto> orderPayments){
        BigDecimal methodsAmount = BigDecimal.ZERO;
        for (OrderPaymentRequestDto payment : orderPayments){
            methodsAmount = methodsAmount.add(payment.getAmount());
        }

        return methodsAmount;
    }

    /**
     * Validamos que el stock introducido no supere el permitido
     * @param cartItems
     * @return
     */
    private Product validateProductsStock(List<CartItem> cartItems){
        for (CartItem cartItem : cartItems){
            Product product = productService.getProductById(cartItem.getProduct().getId());
            if (cartItem.getQuantity() > product.getStock()){
                return product;
            }
        }

        return null;
    }

}
