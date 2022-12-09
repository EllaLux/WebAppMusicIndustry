package org.larina.application.entities;

import org.springframework.security.core.GrantedAuthority;

//перечисление
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name(); //строковое представление роли
    }
}

