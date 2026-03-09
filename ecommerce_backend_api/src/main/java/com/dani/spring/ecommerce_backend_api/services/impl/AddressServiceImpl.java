package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.dto.requests.AddressRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.AddressUpdateRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.addresses.Address;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.repositories.AddressRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.AddressService;
import com.dani.spring.ecommerce_backend_api.utils.AddressUtility;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository repository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public List<Address> getAllUserAddresses(String username) {
        User user = getUserByUsername(username);
        return getAllUserAddresses(user);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Address> getAllUserAddresses(User user) {
        return repository.findByUser(user);
    }

    @Transactional(readOnly=true)
    @Override
    public Address getUserAddressById(Long addressId, String username) {
        User user = getUserByUsername(username);
        return getUserAddressById(addressId, user);
    }

    @Transactional(readOnly=true)
    @Override
    public Address getUserAddressById(Long addressId, User user) {
        Address address = repository.findByUserAndId(user, addressId);
        if (address == null){
            throw new EntityNotFoundException("No se ha encontrado la direccion indicada");
        }

        return address;
    }

    @Transactional
    @Override
    public Address createAddress(AddressRequestDto request, String username){
        User user = getUserByUsername(username);
        Address address = AddressUtility.getAddresFromRequest(request, user);

        //En caso de ser marcado como principal desactivamos el resto
        if (address.isDefault()){
            disableAddresses(user);
        }

        return repository.save(address);
    }

    @Transactional
    @Override
    public Address updateAddress(AddressUpdateRequestDto request, Long addressId, String username) {
        User user = getUserByUsername(username);
        Address address = getUserAddressById(addressId, user);
        //Seteamos valores que vengan informados
        address.setName(request.getName() != null ? request.getName() : address.getName());
        address.setStreet(request.getStreet() != null ? request.getStreet() : address.getStreet());
        address.setNumber(request.getNumber() != null ? request.getNumber() : address.getNumber());
        address.setFloor(request.getFloor() != null ? request.getFloor() : address.getFloor());
        address.setDoor(request.getDoor() != null ? request.getDoor() : address.getDoor());
        address.setCountry(request.getCountry() != null ? request.getCountry() : address.getCountry());
        address.setCity(request.getCity() != null ? request.getCity() : address.getCity());
        address.setPostalCode(request.getPostalCode() != null ? request.getPostalCode() : address.getPostalCode());
        address.setDefault(request.isDefault() ? request.isDefault() : address.isDefault());

        //En caso de ser marcado como principal desactivamos el resto
        if(request.isDefault()){
            disableAddresses(user);
        }

        return repository.save(address);
    }

    @Transactional
    @Override
    public void deleteAddress(Long addressId, String username) {
        User user = getUserByUsername(username);
        Address address = getUserAddressById(addressId, user);
        repository.delete(address);
    }

    @Transactional
    @Override
    public void changeAddressDefault(Long addressId, String username) {
        User user = getUserByUsername(username);
        Address address = getUserAddressById(addressId, user);

        //Desactivamos el que tenga ya marcado como principal
        disableAddresses(user);

        //Marcamos como principal y guardamos
        address.setDefault(true);
        repository.save(address);
    }


    @Transactional(readOnly=true)
    private User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow();
    }

    /**
     * Metodo que desactiva la direccion marcada como principal
     * @param user
     */
    @Transactional
    private void disableAddresses(User user){
        List<Address> addresses = getAllUserAddresses(user);
        for (Address address : addresses){
            if (address.isDefault()){
                address.setDefault(false);
                repository.save(address);
                break;
            }
        }
    }



}
