package com.example.mapper;

import com.example.entity.Chat;
import com.example.entity.User;
import com.example.dto.ChatDto;
import com.example.dto.CreateChatDto;

public class ChatMapper implements Mapper<Chat, ChatDto> {

    private static final ChatMapper INSTANCE = new ChatMapper();

    @Override
    public ChatDto mapFrom(Chat object) {
        return ChatDto.builder()
                .id(object.getId())
                .creator(object.getCreator())
                .recipient((User) object.getRecipient())
                .name(object.getName())
                .build();
    }

    public Chat createChatMapper(CreateChatDto object) {
        return Chat.builder()
                .creator(object.getCreator())
                .name(object.getName())
                .build();
    }

    public static ChatMapper getInstance() {
        return INSTANCE;
    }

}
