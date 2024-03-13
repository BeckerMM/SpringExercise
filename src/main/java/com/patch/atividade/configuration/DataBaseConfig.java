package com.patch.atividade.configuration;

import com.patch.atividade.model.Authorization;
import com.patch.atividade.model.entity.User;
import com.patch.atividade.model.entity.UserDetailsEntity;
import com.patch.atividade.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@AllArgsConstructor
public class DataBaseConfig {
    private final UserRepository userRepository;
    @PostConstruct
    public void init(){
        User user = new User();
        user.setName("Teste");
        user.setUserDetailsEntity(
                UserDetailsEntity.builder()
                        .user(user)
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .username("teste@gmail")
                        .authorities(List.of(Authorization.GET))
                        .password(new BCryptPasswordEncoder().encode("teste123"))
                        .build());

        userRepository.save(user);
    }
}
