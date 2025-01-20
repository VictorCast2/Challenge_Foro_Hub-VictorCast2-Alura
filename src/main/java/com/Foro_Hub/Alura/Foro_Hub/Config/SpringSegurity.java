package com.Foro_Hub.Alura.Foro_Hub.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("removal")
@Configuration
@EnableWebSecurity
public class SpringSegurity {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF si es necesario
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/topicos").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/topicos/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/topicos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/respuestas").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/respuestas/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/respuestas/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic();  // Mantener autenticación básica
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails usuario1 = User.builder()
                .username("usuario1")
                .password(passwordEncoder().encode("contraseña1"))
                .roles("USER")
                .build();
        UserDetails usuario2 = User.builder()
                .username("usuario2")
                .password(passwordEncoder().encode("contraseña2"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(usuario1, usuario2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}