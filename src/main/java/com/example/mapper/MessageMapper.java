package com.example.mapper;


import com.example.entity.Message;
import com.example.dto.CreateMessageDto;
import com.example.dto.MessageDto;

public class MessageMapper implements Mapper<Message, MessageDto> {

    private static final MessageMapper INSTANCE = new MessageMapper();

    @Override
    public MessageDto mapFrom(Message object) {
        return MessageDto.builder()
                .id(object.getId())
                .text(object.getText())
                .chatId(object.getChatId())
                .creatorId(object.getCreatorId())
                .build();
    }

    public Message mapFrom(CreateMessageDto object) {
        return Message.builder()
                .text(object.getText())
                .chatId(object.getChatId())
                .creatorId(object.getCreatorId())
                .build();
    }

    public static MessageMapper getInstance() {
        return INSTANCE;
    }

}
