package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.entities.Role;
import com.dani.spring.ecommerce_backend_api.entities.User;
import com.dani.spring.ecommerce_backend_api.repositories.RoleRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly=true)
    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        List<Role> roles = getUserRolesList(user.isAdmin());
        user.setRoles(roles);
        user.setPassword(encodePasswd(user.getPassword()));

        return repository.save(user);
    }

    //Cifrar password
    private String encodePasswd(String str){
        return passwordEncoder.encode(str);
    }

    //Asignar roles a un usuario al crearlo
    private List<Role> getUserRolesList(boolean isAdmin){
        List<Role> roles = new ArrayList<>();

        Optional<Role> optRoleUser = roleRepository.findByName("ROLE_USER");
        optRoleUser.ifPresent(role -> roles.add(role));

        if(isAdmin){
            Optional<Role> optRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optRoleAdmin.ifPresent(role -> roles.add(role));
        }
        
        return roles;
    }

}
