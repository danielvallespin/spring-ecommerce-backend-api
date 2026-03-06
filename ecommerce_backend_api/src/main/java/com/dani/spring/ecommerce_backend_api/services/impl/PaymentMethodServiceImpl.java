package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.dto.requests.PaymentMethodRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.payment_method.PaymentMethod;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.repositories.PaymentMethodRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.PaymentMethodService;
import com.dani.spring.ecommerce_backend_api.utils.PaymentMethodUtility;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{

    @Autowired
    PaymentMethodRepository repository;

    @Autowired
    UserRepository userRepository;

    
    @Transactional(readOnly=true)
    @Override
    public List<PaymentMethod> getAllUserPaymentMethods(User user) {
        return repository.findByUser(user);
    }

    @Transactional(readOnly=true)
    @Override
    public List<PaymentMethod> getAllUserPaymentMethods(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return getAllUserPaymentMethods(user);
    }

    @Transactional(readOnly=true)
    @Override
    public PaymentMethod getPaymentMethodById(Long paymentId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        PaymentMethod payment = repository.findByUserAndId(user, paymentId);
        if (payment == null){
            throw new EntityNotFoundException("No se ha encontrado el metodo de pago indicado");
        }

        return payment;
    }

    @Transactional(readOnly=true)
    @Override
    public PaymentMethod getByUserAndLast4(String last4, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return repository.findByUserAndLast4(user, last4);
    }

    @Transactional
    @Override
    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod) {
        return repository.save(paymentMethod);
    }

    @Transactional
    @Override
    public PaymentMethod createPaymentMethod(PaymentMethodRequestDto request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        //Convertimos el request y user en un objeto PaymentMethod
        PaymentMethod payment = PaymentMethodUtility.getPaymentMethod(request, user);
        payment.setEnabled(true);

        //Si el nuevo metodo es default debemos quitar el default de sus otros metodos
        if (payment.isDefault()){
            disbaleDefaultFromOtherPayments(user);
        }

        return repository.save(payment);
    }

    @Transactional
    @Override
    public PaymentMethod setPaymentAsDefault(Long paymentId, String username) {
        //Primero miramos que le metodo de pago indicado exista
        PaymentMethod payment = getPaymentMethodById(paymentId, username);

        //Ahora buscamos si tenia otro metodo como default para quitarselo
        disbaleDefaultFromOtherPayments(username);
        
        //Guardamos el metodo indicado como default
        payment.setDefault(true);
        return repository.save(payment);
    }

    @Transactional
    @Override
    public void disbaleDefaultFromOtherPayments(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        disbaleDefaultFromOtherPayments(user);
    }

    @Transactional
    @Override
    public void disbaleDefaultFromOtherPayments(User user) {
        List<PaymentMethod> allUserMethods = getAllUserPaymentMethods(user);
        if (allUserMethods != null && !allUserMethods.isEmpty()){
            for (PaymentMethod paymentDb : allUserMethods){
                if (paymentDb.isDefault()){
                    paymentDb.setDefault(false);
                    repository.save(paymentDb);
                    break;
                }
            }
        }
    }

    @Transactional
    @Override
    public void disablePaymentMethod(Long paymentId, String username) {
        //Obtenemos el metodo de pago (si no existe 404)
        PaymentMethod payment = getPaymentMethodById(paymentId, username);
        //Dar de baja
        payment.setEnabled(false);
        repository.save(payment);
    }




}
