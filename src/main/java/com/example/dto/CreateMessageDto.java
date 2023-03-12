package com.example.dto;

import com.example.entity.Chat;
import com.example.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateMessageDto {

    @NotNull
    @Size(min = 1, max = 300)
    String text;
    @NotNull
    Chat chatId;
    @NotNull
    Long creatorId;
}
