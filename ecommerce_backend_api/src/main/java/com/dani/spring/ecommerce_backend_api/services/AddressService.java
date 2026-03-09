package com.dani.spring.ecommerce_backend_api.services;

import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.requests.AddressRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.AddressUpdateRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.addresses.Address;
import com.dani.spring.ecommerce_backend_api.entities.user.User;

public interface AddressService {

    List<Address> getAllUserAddresses(String username);

    List<Address> getAllUserAddresses(User user);

    Address getUserAddressById(Long addressId, String username);

    Address getUserAddressById(Long addressId, User user);

    Address createAddress(AddressRequestDto request, String username);

    Address updateAddress(AddressUpdateRequestDto request, Long addressId, String username);

    void deleteAddress(Long addressId, String username);

    void changeAddressDefault(Long addressId, String username);

}
