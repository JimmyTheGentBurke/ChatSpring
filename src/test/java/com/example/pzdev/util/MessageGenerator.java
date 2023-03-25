package com.example.pzdev.util;

import com.example.entity.Chat;
import com.example.entity.Message;
import com.example.entity.User;

import java.util.UUID;

public class MessageGenerator {
    public static Message CreateMessages(User user, Chat chat) {
        return Message.builder()
                .text("text" + UUID.randomUUID())
                .chat(chat)
                .creator(user)
                .build();

    }

}
