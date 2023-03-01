package com.example.dto;

import com.example.entity.Chat;
import com.example.entity.User;
import lombok.Data;
import lombok.Builder;
import lombok.Value;
@Data
@Value
@Builder
public class CreateMessageDto {
    String text;
    Chat chatId;
    User creatorId;
}
