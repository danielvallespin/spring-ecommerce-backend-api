package com.dani.spring.ecommerce_backend_api.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.dani.spring.ecommerce_backend_api.security.filter.JwtAuthenticationFilter;
import com.dani.spring.ecommerce_backend_api.security.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            // Deshabilitar CSRF porque usamos JWT
            .csrf(csrf -> csrf.disable())

            // Configuración de CORS
            .cors(cors -> cors.configurationSource(configurationSource()))

            // Configurar las reglas de autorización
            .authorizeHttpRequests(authz -> authz
                // Permitir preflight OPTIONS
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // Rutas públicas
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/product", "/product/**").permitAll()
                // Swagger y OpenAPI
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )

            // JWT filters
            .addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(new JwtValidationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)

            // Stateless: no sesiones HTTP
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .build();
    }

    //Creacion de config para cors para consumo de api en clientes
    @Bean
    CorsConfigurationSource configurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*")); //Origenes permitidos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT")); //Metodos permitidos
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); //Header permitidos
        config.setAllowCredentials(true); //Requiere credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(new CorsFilter(configurationSource()));
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE); //Aplicar prioridad maxima a cors
        return corsBean;
    }

}
