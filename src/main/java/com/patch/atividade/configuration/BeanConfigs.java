package com.patch.atividade.configuration;

import com.patch.atividade.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class BeanConfigs {

    @Bean
    public CorsConfiguration corsConfig(){
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfig.setAllowedMethods(List.of("POST","");
        corsConfig.setAllowCredentials(true);
    }

    private final AuthenticationService authenticationService;
//    @Autowired
//    public void config(AuthenticationManagerBuilder auth,
//                       AuthenticationService authenticationService) throws Exception {
//        auth.userDetailsService(authenticationService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new HttpSessionSecurityContextRepository(); // Mantém a seção do úsuario na requisição
    }

//    @Bean
//    public UserDetailsService userDatailsService(
//            AuthenticationService authenticationService){
//    return authenticationService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
//        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(authenticationService);
        return new ProviderManager(dao);
    }


}
