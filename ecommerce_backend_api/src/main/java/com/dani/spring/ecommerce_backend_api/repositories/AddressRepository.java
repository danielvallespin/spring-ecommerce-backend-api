package com.dani.spring.ecommerce_backend_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dani.spring.ecommerce_backend_api.entities.addresses.Address;
import com.dani.spring.ecommerce_backend_api.entities.user.User;


public interface AddressRepository extends JpaRepository<Address, Long>{

    List<Address> findByUser(User user);

    Address findByUserAndId(User user, Long addressId);

}
