package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.requests.ChangePasswordRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.UserAdminRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.requests.UserRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.UserAdminResponseDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.UserResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.user.User;
import com.dani.spring.ecommerce_backend_api.services.UserService;
import com.dani.spring.ecommerce_backend_api.utils.UserUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users", description = "API para la gestion de usuarios")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService service;

    //GET_ALL
    @Operation(summary = "Obtener todos los usuarios del sistema (solo para admins)")
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200", 
                description = "Lista de usuarios obtenida correctamente", 
                content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserAdminResponseDto.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserAdminResponseDto> list(){
        List<User> users = service.getAllusers();
        return UserUtility.getListOfUserAdminResponse(users);
    }

    //GET_BY_ID
    @Operation(summary = "Obtener usuario por id (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto obtenida correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserAdminResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el producto indicado", content = @Content),
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserAdminResponseDto> getUserById(@PathVariable Long id){
        User user = UserUtility.getUserFromOptionalOrThrow(service.getUserById(id), id);
        return ResponseEntity.ok(UserUtility.getUserAdminResponse(user));
    }

    //CREATE (ADMIN)
    @Operation(summary = "Crear usuario con capacidad de hacerlo admin (solo para admins)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserAdminResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserAdminRequestDto user){
        User newUser = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserUtility.getUserAdminResponse(newUser));
    }

    //CREATE (NORMAL)
    @Operation(summary = "Crear usuario normal (SIN JWT)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación", content = @Content)
    })
    @PostMapping("/singup")
    public ResponseEntity<?> singup(@Valid @RequestBody UserRequestDto user){
        User newUser = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserUtility.getUserResponse(newUser));
    }


    //GET_MY_USER
    @Operation(summary = "Obtener tu propio usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyUser(Principal principal){
        Optional<User> optUser = service.getUserByUsername(principal.getName());
        User user = UserUtility.getUserFromOptionalOrThrow(optUser);
        return ResponseEntity.ok(UserUtility.getUserResponse(user));
    }

    //DELTE_BY_ID
    @Operation(summary = "Eliminar usuario por id (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado al usuario indicado", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable Long id){
        Map<String, Object> data = new HashMap<>();

        Optional<User> optUser = service.getUserById(id);
        User user = UserUtility.getUserFromOptionalOrThrow(optUser, id);

        service.deleteUserById(user.getId());
        data.put("message", "El usuario ha sido eliminado correctamente");
        data.put("userId", user.getId());
        data.put("username", user.getUsername());
        return ResponseEntity.ok(data);
    }

    //CHANGE_PASSWORD
    @Operation(summary = "Cambiar la contraseña de tu usario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario habilitado correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se ha enocntrado al usuario indicado", content = @Content)
    })
    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/password")
    public ResponseEntity<Map<String, String>> changePassword(Principal principal, @RequestBody ChangePasswordRequestDto request){
        Map<String, String> data = new HashMap<>();

        Optional<User> optUser = service.getUserByUsername(principal.getName());
        User user = UserUtility.getUserFromOptionalOrThrow(optUser);
        user.setPassword(service.encodePasswd(request.getPassword()));
        service.saveUser(user);
        data.put("message", "Contraseña modificada correctamente");
        return ResponseEntity.ok(data);
    }

    //ENABLE_BY_ID
    @Operation(summary = "Habilitar usuario por id (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario habilitado correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se ha enocntrado al usuario indicado", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/enable/{id}")
    public ResponseEntity<Map<String, String>> enableUserById(@PathVariable Long id){
        Map<String, String> data = new HashMap<>();

        Optional<User> optUser = service.getUserById(id);
        User user = UserUtility.getUserFromOptionalOrThrow(optUser, id);
        user.setEnabled(true);
        service.saveUser(user);
        data.put("message", String.format("El usuario %s ha sido habilitado", user.getUsername()));
        return ResponseEntity.ok(data);
    }


    //ENABLE_BY_ID
    @Operation(summary = "Deshabilitar usuario por id (solo para admins)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario deshabilitado correctamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado al usuario indicado", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/disable/{id}")
    public ResponseEntity<Map<String, String>> disableUserById(@PathVariable Long id){
        Map<String, String> data = new HashMap<>();

        Optional<User> optUser = service.getUserById(id);
        User user = UserUtility.getUserFromOptionalOrThrow(optUser, id);
        user.setEnabled(false);
        service.saveUser(user);
        data.put("message", String.format("El usuario %s ha sido deshabilitado", user.getUsername()));
        return ResponseEntity.ok(data);
    }



}
