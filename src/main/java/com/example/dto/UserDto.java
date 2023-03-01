package com.example.dto;

import lombok.*;

@Value
@Builder
public class UserDto {
    Long id;
    String login;
    String nickName;
    String password;

}
