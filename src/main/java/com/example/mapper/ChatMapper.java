package com.example.mapper;

import com.example.dto.ChatDto;
import com.example.dto.CreateChatDto;
import com.example.entity.Chat;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper implements Mapper<Chat, ChatDto> {


    @Override
    public ChatDto mapFrom(Chat object) {
        return ChatDto.builder()
                .id(object.getId())
                .creator(object.getCreator())
                .name(object.getName())
                .build();
    }

    public Chat createChatMapper(CreateChatDto object) {
        return Chat.builder()
                .creator(object.getCreator())
                .name(object.getName())
                .build();
    }


}
