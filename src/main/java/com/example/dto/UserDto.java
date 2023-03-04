package com.example.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    Long id;
    String login;
    String nickName;
    String password;

}
