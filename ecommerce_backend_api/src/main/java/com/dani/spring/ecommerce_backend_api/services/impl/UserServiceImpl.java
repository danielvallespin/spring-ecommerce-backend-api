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
import com.dani.spring.ecommerce_backend_api.dto.responses.UserAdminResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.UserResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.Role;
import com.dani.spring.ecommerce_backend_api.entities.User;
import com.dani.spring.ecommerce_backend_api.exceptions.UsernameAlreadyExistsException;
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
    public List<UserAdminResponseDto> getAllusers() {
        List<User> users = (List<User>) repository.findAll();
        return getListOfUserAdminResponse(users);
    }

    @Override
    @Transactional(readOnly=true)
    public UserAdminResponseDto getUserResponseById(Long id){
        Optional<User> optUser = getUserById(id);
        if (optUser.isPresent()){
            return getUserAdminResponse(optUser.orElseThrow());
        }
        return null;
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<User> getUserById(Long id){
        return  repository.getById(id);
    }

    @Override
    @Transactional
    public UserResponseDto saveUser(UserRequestDto user) {
        //Validamos que no exista el username y si existe lanzamos excepcion controlada
        if (repository.existsByUsername(user.getUsername())){
            throw new UsernameAlreadyExistsException(String.format("El username %s ya existe, debe utilizar otro.", user.getUsername()));
        }
        //Creamos el nuevo usuario y lo guardamos
        User newUser = new User(user.getEmail(), user.getUsername(), user.getPassword());
        Boolean admin = false;
        if (user instanceof UserAdminRequestDto adminDto) {
            admin = adminDto.isAdmin();
        }
        List<Role> roles = getUserRolesList(admin);
        newUser.setRoles(roles);
        newUser.setPassword(encodePasswd(user.getPassword()));
        
        repository.save(newUser);

        //Devolvemos entidad mas o menos completa segun el rol
        if (admin){
            return getUserAdminResponse(newUser);
        }
        return new UserResponseDto(newUser.getUsername(), newUser.getEmail());
    }

    @Override
    @Transactional(readOnly=true)
    public UserResponseDto getMyUserResponse(String username) {
        User user = getMyUser(username).orElseThrow();
        return new UserResponseDto(user.getUsername(), user.getEmail());
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<User> getMyUser(String username) {
        return repository.getByUsername(username);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id){
        repository.deleteById(id);
    }

    /**
     * Metodo que devuelve una password ya cifrada 
     * @param str
     * @return
     */
    private String encodePasswd(String str){
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

    /**
     * Metodo para obtener un lista de la respuesta dto de user admin
     * @param users
     * @return
     */
    private List<UserAdminResponseDto> getListOfUserAdminResponse(List<User> users){
        List<UserAdminResponseDto> response = new ArrayList<>();
        for (User user : users){
            response.add(getUserAdminResponse(user));
        }

        return response;
    }

    /**
     * Metodo para obtener un objeto de respuesta dto de user admin
     * @param user
     * @return
     */
    private UserAdminResponseDto getUserAdminResponse(User user){
        return new UserAdminResponseDto(
            user.getUsername(), 
            user.getEmail(), 
            user.isEnabled(), 
            getListRolesOfUser(user.getRoles()));
    }

    /**
     * Metodo para obtener una lista con los roles de un usuario
     * @param roles
     * @return
     */
    private List<String> getListRolesOfUser(List<Role> roles){
        List<String> rolesList = new ArrayList<>();
        for(Role role : roles){
            rolesList.add(role.getName());
        }

        return rolesList;
    }


}
