package com.dani.spring.ecommerce_backend_api.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dani.spring.ecommerce_backend_api.entities.User;
import com.dani.spring.ecommerce_backend_api.security.TokenJwtData;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //Metodo que sirve para hacer el login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user = null;
        String username = null;
        String password = null;

        try {
            //login
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            //extraer datos del front
            username = user.getUsername();
            password = user.getPassword();
        } catch (StreamReadException | DatabindException ex) {
            System.getLogger(JwtAuthenticationFilter.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(JwtAuthenticationFilter.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        //Crear token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        //Autenticar
        return authenticationManager.authenticate(authenticationToken);
    }

    //Metodo que sirve para autenticarnos
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //Obtenemos el username y token del usuario autenticado
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

        //Crear los claims (los claims son los datos que viajan en el token)
        Claims claims = Jwts.claims()
            .add("authorities", new ObjectMapper().writeValueAsString(roles))
            .add("username", username)
            .build(); //Extiende de Map

        //Crear el Jwt
        String token = Jwts.builder()
                        .subject(username)
                        .claims(claims)
                        .expiration(new Date(System.currentTimeMillis() + 36000000)) //1h de token
                        .issuedAt(new Date()) //Fecha creacion
                        .signWith(TokenJwtData.SECRET_KEY)
                        .compact();
        //Enviar token al cliente
        response.addHeader(TokenJwtData.HEADER_AUTHORIZATION, TokenJwtData.PREFIX_TOKEN + token);
        //Cramos un mapa con los datos a devoler
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", String.format("Hola %s has iniciado sesion con exito!", username));
        //Agregar el mapa al response
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        //Tipo de respuesta y estado a devolver
        response.setContentType(TokenJwtData.CONTENT_TYPE);
        response.setStatus(200);
    }

    //Para cuando ha habido algun error
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticacion. El username o password es incorrecto");
        body.put("error", failed.getMessage());
        //Agregar el mapa al response
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        //Tipo de respuesta y estado a devolver
        response.setContentType(TokenJwtData.CONTENT_TYPE);
        response.setStatus(401);
    }

}
