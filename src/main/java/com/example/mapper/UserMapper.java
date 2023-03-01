package com.example.mapper;

import com.example.dto.UserDto;
import com.example.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .login(object.getLogin())
                .nickName(object.getNickName())
                .build();
    }

    public User mapFormDto(UserDto object){
        return User.builder()
                .id(object.getId())
                .login(object.getLogin())
                .nickName(object.getNickName())
                .password(object.getPassword())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }

}
