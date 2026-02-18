package com.dani.spring.ecommerce_backend_api.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import com.dani.spring.ecommerce_backend_api.security.SimpleGrantedAuthorityJsonCreator;
import com.dani.spring.ecommerce_backend_api.security.TokenJwtData;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = request.getRequestURI();
        String method = request.getMethod().toUpperCase();

        // Saltar rutas públicas
        List<String> publicPaths = TokenJwtData.PUBLIC_ROUTES.get(method);
        if (publicPaths != null) {
            for (String publicPath : publicPaths) {
                if (pathMatcher.match(publicPath, path)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        String header = request.getHeader(TokenJwtData.HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(TokenJwtData.PREFIX_TOKEN)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("{\"error\":\"Token JWT no proporcionado\"}");
            response.setContentType(TokenJwtData.CONTENT_TYPE);
            return;
        }

        String token = header.replace(TokenJwtData.PREFIX_TOKEN, "");
        try{
            //Validar token
            Claims claims = Jwts.parser().verifyWith(TokenJwtData.SECRET_KEY).build().parseSignedClaims(token).getPayload();
            //Los nombre del get son los asignados al calims en la clase JwtAuthenticationFilter
            String username = (String) claims.get("username");
            Object authoritiesClaims = claims.get("authorities");

            //Convertir roles a objeto spring (el authoritiesClaims en una collection)
            Collection<? extends GrantedAuthority> authorities = 
                    Arrays.asList(
                        new ObjectMapper()
                        .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                        .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));
                        
            //Creamos una autenticacion
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            //Guardar autenticacion (IMPORTANTE)
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //Continuar a la petición
            chain.doFilter(request, response);

        } catch(JwtException e){
            //En caso de que el token no sea valido
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "El token JWT es invalido!");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(TokenJwtData.CONTENT_TYPE);
        }
    }


}
