package com.example.dto;

import com.example.entity.Chat;
import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class MessageDto {
    Long id;
    String text;
    Chat chatId;
    User creatorId;

}
