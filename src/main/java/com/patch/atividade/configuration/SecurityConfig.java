package com.patch.atividade.configuration;

import com.patch.atividade.FilterAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final SecurityContextRepository repo;
    private final FilterAuthentication filterAuthentication;

    @Bean
    public SecurityFilterChain config (HttpSecurity http) throws Exception {
        // Prevenção ao ataque CSRF (Cross-Site Request Forgery)
        http.csrf(config -> config.disable());
        http.authorizeHttpRequests( authz -> authz
                .requestMatchers(HttpMethod.GET,"/teste").authenticated()
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated());
        // Manter a sessão do usuário na requisição ativa
//        http.securityContext((context) -> context.securityContextRepository(repo));

        http.formLogin(Customizer.withDefaults()); // este metodo habilita o formulario de login do spring security
        http.logout(Customizer.withDefaults()); // este metodo habilita o logout do spring security

        http.sessionManagement( config -> {
            config.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.addFilterBefore(filterAuthentication, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}