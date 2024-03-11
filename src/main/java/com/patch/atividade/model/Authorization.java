package com.patch.atividade.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor
public enum Authorization implements GrantedAuthority {
    GET("Get"),
    POST("Post"),
    PUT("Put"),
    DELETE("Delete");

    private String name;
    @Override
    public String getAuthority() {
        return name;
    }
}
