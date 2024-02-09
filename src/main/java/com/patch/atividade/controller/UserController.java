package com.patch.atividade.controller;

import com.patch.atividade.model.dto.UserDTO;
import com.patch.atividade.model.entity.Archive;
import com.patch.atividade.model.entity.User;
import com.patch.atividade.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public void register(@RequestParam String userDTO , @RequestParam List<MultipartFile> files) throws Exception {
        userService.register(userDTO, files);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        try {
            userService.update(id, userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        return
                new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity<User> patchStatus(@RequestParam boolean status, @RequestParam Integer id) {
        try {
            userService.patchStatus(status, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/password")
    public ResponseEntity<User> patchPassword(@RequestParam String password, @RequestParam Integer id) {
        try {
            userService.patchPassword(password, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
