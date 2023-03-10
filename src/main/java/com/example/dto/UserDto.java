package com.example.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    Long id;
    @NotNull
    @Size(min = 6, max = 32)
    String login;
    @NotNull
    @Size(min = 6, max = 32)
    String nickName;
    @NotNull
    @Size(min = 6, max = 32)
    String password;

}
