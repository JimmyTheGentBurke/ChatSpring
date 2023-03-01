package com.example.mapper;


import com.example.dto.CreateUserDto;
import com.example.entity.User;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .login(object.getLogin())
                .nickName(object.getNickName())
                .password(object.getPassword())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

}
