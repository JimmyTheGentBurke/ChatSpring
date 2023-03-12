package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateUserDto {
    @NotNull
    @Size(min = 6, max = 32)
    String login;
    @NotNull
    @Size(min = 6, max = 32)
    String nickName;
    @NotNull
    @Size(min = 6, max = 32)
    String password;
    @NotNull
    String role;

}
