package com.example.dto;

import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ChatDto {
    Long id;
    User creator;
    User recipient;
    String name;

}
