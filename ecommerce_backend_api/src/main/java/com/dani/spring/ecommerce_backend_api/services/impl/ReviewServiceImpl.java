package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.dto.requests.ReviewRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.ReviewUpdateRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.order.Order;
import com.dani.spring.ecommerce_backend_api.entities.order.OrderItem;
import com.dani.spring.ecommerce_backend_api.entities.product.Product;
import com.dani.spring.ecommerce_backend_api.entities.reviews.Review;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.repositories.OrderRepository;
import com.dani.spring.ecommerce_backend_api.repositories.ReviewRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.ReviewService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Transactional(readOnly=true)
    @Override
    public List<Review> getAllByProduct(Product product) {
        return repository.findByProductOrderByPurchasedDesc(product);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Review> getAllMyReviews(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return repository.findByUser(user);
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<Review> getOptionalReview(Product product, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return repository.findByProductAndUser(product, user);
    }

    @Transactional(readOnly=true)
    @Override
    public Review getReview(Product product, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return getReview(product, user);
    }

    @Transactional(readOnly=true)
    @Override
    public Review getReview(Product product, User user) {
        Optional<Review> optReview = repository.findByProductAndUser(product, user);
        if (!optReview.isPresent()){
            throw new EntityNotFoundException("No se ha encontrado ningúna review para el producto con id: " + product.getName());
        }

        return optReview.orElseThrow();
    }

    @Transactional
    @Override
    public Review createReview(Product product, String username, ReviewRequestDto request) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Review review = new Review(
            user,
            product,
            request.getRating(),
            request.getTitle(),
            request.getComment(),
            isProductPurchased(user, product.getId()) //Validar si se ha comprado
        );

        return repository.save(review);
    }

    @Transactional
    @Override
    public Review updateReview(Product product, String username, ReviewUpdateRequestDto request) {
        //Obtenemos el user
        User user = userRepository.findByUsername(username).orElseThrow();
        //Obtenemos la review (sino existe devuele 404)
        Review review = getReview(product, user);
        //Cambiamos los datos que vengan informados en el request
        review.setRating(request.getRating() != null ? request.getRating() : review.getRating());
        review.setTitle(request.getTitle() != null ? request.getTitle() : review.getTitle());
        review.setComment(request.getComment() != null ? request.getComment() : review.getComment());
        //Validar si se ha comprado
        review.setPurchased(isProductPurchased(user, product.getId()));

        return repository.save(review);
    }

    @Override
    public void deleteReview(Product product, String username) {
        //Obtenemos la review (sino existe devuele 404)
        Review review = getReview(product, username);
        repository.delete(review);
    }

    /**
     * Metodo que valida si un producto ha sido comprado por el usuario
     * @param user
     * @param productId
     * @return
     */
    @Transactional(readOnly=true)
    private boolean isProductPurchased(User user, Long productId){
        boolean isProductPurchased = false;
        List<Order> userOrders = orderRepository.findByUser(user);
        if (!userOrders.isEmpty()){
            for (Order order : userOrders){
                if (isProductPurchased) break;
                for (OrderItem orderItem : order.getItems()){
                    if (orderItem.getProduct().getId().equals(productId)){
                        isProductPurchased = true;
                        break;
                    }
                }
            }
        }

        return isProductPurchased;
    }

}
