package com.example.dto;

import com.example.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateChatDto {
    @NotNull
    User creator;
    @NotNull
    String name;



}
