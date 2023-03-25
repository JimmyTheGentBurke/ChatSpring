package com.example.pzdev.service;

import com.example.dto.UserDto;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.mapper.CreateUserMapper;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserService.class})
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CreateUserMapper createUserMapper;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;


    @Test
    void findByUsernameTest() {

        Optional<User> user = Optional.of(User.builder().username("Boris").build());
        UserDto borisUser = UserDto.builder().username("borisUser").build();

        Mockito.doReturn(user).when(userRepository).findByUsername("Boris");
        Mockito.doReturn(borisUser).when(userMapper).mapFrom(user.get());

        Optional<UserDto> resultDto = userService.findByUsername("Boris");

        assertThat(resultDto).isPresent();
        assertThat(resultDto.get().getUsername()).isEqualTo("borisUser");
    }
    @Test
    void findByNicknameTest(){
        Optional<User> user = Optional.of(User.builder().nickname("Boris").build());
        UserDto borisUser = UserDto.builder().nickname("borisUser").build();

        Mockito.doReturn(user).when(userRepository).findByNicknameIgnoreCase("Boris");
        Mockito.doReturn(borisUser).when(userMapper).mapFrom(user.get());

        Optional<UserDto> resultDto = userService.findByNickname("Boris");

        assertThat(resultDto).isPresent();
        assertThat(resultDto.get().getNickname()).isEqualTo("borisUser");

    }
    @Test
    void findUsersByChatId(){

        List<User> user = new ArrayList<>();

        user.add(User.builder().id(1L).build());

        Mockito.doReturn(user).when(userRepository).findUsersByChatId(1L);

    }

}
