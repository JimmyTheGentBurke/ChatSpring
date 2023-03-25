package com.example.pzdev.util;

import com.example.entity.Role;
import com.example.entity.User;

import java.util.UUID;

public class UserGenerator {
    public static User createUser() {
        return User.builder()
                .username("login" + UUID.randomUUID())
                .nickname("nickName" + UUID.randomUUID())
                .password(UUID.randomUUID().toString())
                .role(Role.USER)
                .build();
    }

}

