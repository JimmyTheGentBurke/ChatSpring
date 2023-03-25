package com.example.pzdev.util;

import com.example.entity.Chat;
import com.example.entity.User;

import java.util.UUID;

public class ChatGenerator {
    public static Chat createChat(User user) {
        return Chat.builder()
                .creator(user)
                .name("name" + UUID.randomUUID())
                .build();
    }

}
