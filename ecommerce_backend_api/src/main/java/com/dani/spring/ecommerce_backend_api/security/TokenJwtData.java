package com.dani.spring.ecommerce_backend_api.security;

import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

//Clase donde tendremos los datos JWT necesarios para el token
public class TokenJwtData {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    // Rutas que no requieren JWT
    public static final Map<String, List<String>> PUBLIC_ROUTES = Map.of(
            "GET", List.of(
                    "/product",
                    "/product/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
            ),
            "POST", List.of(
                    "/login"
            )
    );

}
