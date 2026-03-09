package com.dani.spring.ecommerce_backend_api.utils;

import java.util.ArrayList;
import java.util.List;

import com.dani.spring.ecommerce_backend_api.dto.requests.AddressRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.AddressResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.addresses.Address;
import com.dani.spring.ecommerce_backend_api.entities.user.User;

public class AddressUtility {

    /**
     * Metodo que convierte un objeto Address en un AddressResponseDto
     * @param address
     * @return
     */
    public static AddressResponseDto getAddressResponse(Address address){
        return new AddressResponseDto(
            address.getId(),
            address.getUser().getId(),
            address.getName(),
            address.getStreet(),
            address.getNumber(),
            address.getFloor(),
            address.getDoor(),
            address.getCountry(),
            address.getCity(),
            address.getPostalCode(),
            address.isDefault()
        );
    }

    /**
     * Metodo que convierte una lista de Address en una lista de AddressResponseDto
     * @param addresses
     * @return
     */
    public static List<AddressResponseDto> getAddressResponseList(List<Address> addresses){
        List<AddressResponseDto> response = new ArrayList<>();
        for (Address address : addresses){
            response.add(getAddressResponse(address));
        }

        return response;
    }

    /**
     * Metodo que transforma una AddressRequestDto en una Address
     * @param request
     * @param user
     * @return
     */
    public static Address getAddresFromRequest(AddressRequestDto request, User user){
        return new Address(
            user,
            request.getName(),
            request.getStreet(),
            request.getNumber(),
            request.getFloor(),
            request.getDoor(),
            request.getCountry(),
            request.getCity(),
            request.getPostalCode(),
            request.isDefault()
        );
    }

}
