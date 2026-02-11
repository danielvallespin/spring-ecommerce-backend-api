package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.entities.User;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;

//Service para poder hacer login con spring security
@Service
public class JpaUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = repository.getByUsername(username);
        if (optUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("USername %s no existe en el sistema", username));
        }

        User user = optUser.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPassword(), 
                user.isEnabled(), 
                true, 
                true, 
                true, 
                authorities
            );
    }

}
