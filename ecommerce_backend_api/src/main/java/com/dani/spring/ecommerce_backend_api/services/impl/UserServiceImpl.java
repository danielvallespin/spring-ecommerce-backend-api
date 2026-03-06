package com.dani.spring.ecommerce_backend_api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dani.spring.ecommerce_backend_api.dto.requests.UserAdminRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.UserRequestDto;
import com.dani.spring.ecommerce_backend_api.entities.role.Role;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.exceptions.DataAlreadyExistsException;
import com.dani.spring.ecommerce_backend_api.repositories.RoleRepository;
import com.dani.spring.ecommerce_backend_api.repositories.UserRepository;
import com.dani.spring.ecommerce_backend_api.services.UserService;
import com.dani.spring.ecommerce_backend_api.services.WishlistService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private WishlistService wishlistService;

    @Override
    @Transactional(readOnly=true)
    public List<User> getAllusers() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public User getUserById(Long userId){
        Optional<User> optUser = repository.findById(userId);
        if (!optUser.isPresent()){
            throw new EntityNotFoundException("No se ha encontrado ningún usuario con id: " + userId);
        }

        User user = optUser.orElseThrow();
        user.setAdmin(validateAdminRoleUserDb(user.getRoles()));
        
        return user;
    }

    @Override
    @Transactional(readOnly=true)
    public User getUserByUsername(String username) {
        User user = repository.findByUsername(username).orElseThrow();
        user.setAdmin(validateAdminRoleUserDb(user.getRoles()));
        
        return user;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public User createUser(UserRequestDto user) {
        //Validamos que no exista el username y si existe lanzamos excepcion controlada
        if (repository.existsByUsername(user.getUsername())){
            throw new DataAlreadyExistsException(String.format("El username %s ya existe, debe utilizar otro.", user.getUsername()));
        }
        //Creamos el nuevo usuario y lo guardamos
        User newUser = new User(user.getEmail(), user.getUsername(), encodePasswd(user.getPassword()));
        Boolean admin = false;
        if (user instanceof UserAdminRequestDto adminDto) {
            admin = adminDto.isAdmin();
            newUser.setEnabled(adminDto.isEnabled());
        }
        List<Role> roles = getUserRolesList(admin);
        newUser.setRoles(roles);
        
        saveUser(newUser);
        //Creamos un carrito y lo asignamos a este usuario
        cartService.createCart(newUser);
        //Creamos una lista de deseados inicial y lo asignamos a este usuario
        wishlistService.createWishlist(newUser, "Lista de deseados");

        return newUser;
    }

    @Override
    @Transactional(readOnly=true)
    public boolean isAdmin(String username) {
        User user = getUserByUsername(username);
        return user.isAdmin();
    }

    /**
     * Metodo que devuelve una password ya cifrada 
     * @param str
     * @return
     */
    @Override
    public String encodePasswd(String str){
        return passwordEncoder.encode(str);
    }


    /**
     * Metodo que sirve para asignar los roles a un usuario en su creacion
     * @param isAdmin
     * @return
     */
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

    private boolean validateAdminRoleUserDb(List<Role> roles){
        boolean isAdmin = false;
        for (Role role : roles){
            if ("ROLE_ADMIN".equals(role.getName())){
                isAdmin = true;
                break;
            }
        }

        return isAdmin;
    }

    @Transactional
    @Override
    public String disableUser(Long userId) {
        User user = getUserById(userId);
        user.setEnabled(false);

        String username = user.getUsername();
        cartService.emptyCart(username);

        return username;
    }

}
