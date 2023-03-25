package com.example.pzdev.util;

import com.example.entity.ChatUsers;

public class ChatUsersGenerator {
    public static ChatUsers chatUserCreate(Long chatId, Long userId) {
        return ChatUsers.builder()
                .user(userId)
                .chat(chatId)
                .build();
    }

}
