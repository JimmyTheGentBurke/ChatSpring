package com.example.dto;

import com.example.entity.Chat;
import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class CreateMessageDto {
    String text;
    Chat chatId;
    Long creatorId;
}
