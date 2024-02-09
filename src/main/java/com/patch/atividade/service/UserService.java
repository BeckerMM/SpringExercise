package com.patch.atividade.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patch.atividade.model.dto.UserDTO;
import com.patch.atividade.model.entity.User;
import com.patch.atividade.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public void register(String user, List<MultipartFile> files) throws Exception {
        UserDTO userDTO = objectMapper.readValue(user, UserDTO.class);
        userDTO.setArchives(files);
        User user1 = new User();
        BeanUtils.copyProperties(userDTO, user1);
        userRepository.save(user1);
    }

    public User get(Integer id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void update(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id).get();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(userDTO, user);
        userRepository.save(user);

    }

    public void patchStatus(boolean status, Integer id) {
        User user = userRepository.findById(id).get();
        user.setActive(status);
        userRepository.save(user);
    }

    public void patchPassword(String password, Integer id) {
        User user = userRepository.findById(id).get();
        user.setPassword(password);
        userRepository.save(user);
    }
}
