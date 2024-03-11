package com.patch.atividade.controller;

import com.patch.atividade.model.entity.User;
import com.patch.atividade.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teste")
@AllArgsConstructor
public class TesteController {

    private final UserRepository userRepository;
    @GetMapping()
    public String teste() {
        return "Teste";
    }

    @PostMapping
    public void registerUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
