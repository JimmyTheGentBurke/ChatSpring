package com.example.mapper;


import com.example.dto.CreateMessageDto;
import com.example.dto.MessageDto;
import com.example.entity.Message;
import com.example.entity.User;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements Mapper<Message, MessageDto> {

    @Override
    public MessageDto mapFrom(Message object) {
        return MessageDto.builder()
                .id(object.getId())
                .text(object.getText())
                .chatId(object.getChat())
                .creatorId(object.getCreator())
                .build();
    }

    public Message mapFrom(CreateMessageDto object) {
        return Message.builder()
                .text(object.getText())
                .chat(object.getChatId())
                .creator(new User().setId(object.getCreatorId()))
                .build();
    }

}
