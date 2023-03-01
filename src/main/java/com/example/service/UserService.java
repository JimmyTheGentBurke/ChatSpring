package com.example.service;

import lombok.RequiredArgsConstructor;
import com.example.dto.CreateUserDto;
import com.example.entity.User;
import com.example.mapper.CreateUserMapper;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;

    public Optional<User> create(CreateUserDto userDto){

        return Optional.of(userRepository.save(createUserMapper.mapFrom(userDto)));
    }


}
