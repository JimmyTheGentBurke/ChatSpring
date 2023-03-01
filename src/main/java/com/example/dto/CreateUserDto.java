package com.example.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUserDto {
    String login;
    String nickName;
    String password;

}
