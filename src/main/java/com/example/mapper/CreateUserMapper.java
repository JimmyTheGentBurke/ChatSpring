package com.example.mapper;


import com.example.dto.CreateUserDto;
import com.example.entity.Role;
import com.example.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private final PasswordEncoder passwordEncoder;



    @Override
    public User mapFrom(CreateUserDto object) {

        User build = User.builder()
                .username(object.getLogin())
                .nickName(object.getNickName())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .build();

        Optional.ofNullable(object.getPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(build::setPassword);

        return build;


    }



}
