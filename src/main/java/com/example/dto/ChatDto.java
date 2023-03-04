package com.example.dto;

import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChatDto {
    Long id;
    User creator;
    User recipient;
    String name;

}
