package com.example.mapper;

import com.example.dto.UserDto;
import com.example.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .login(object.getUsername())
                .nickName(object.getNickName())
                .build();
    }

}
