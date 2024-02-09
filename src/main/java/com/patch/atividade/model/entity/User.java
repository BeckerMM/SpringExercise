package com.patch.atividade.model.entity;

import com.patch.atividade.model.entity.Archive;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Archive> archives;



}
