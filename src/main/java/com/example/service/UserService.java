package com.example.service;

import com.example.dto.CreateUserDto;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.mapper.CreateUserMapper;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;
    private final UserMapper userMapper;

    @Transactional
    public Optional<User> create(CreateUserDto userDto) {
        return Optional.of(userRepository.save(createUserMapper.mapFrom(userDto)));
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findByNickName(String nickName) {
        return userRepository.findByNickName(nickName)
                .map(userMapper::mapFrom);

    }

    @Transactional(readOnly = true)
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::mapFrom);

    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserDto> findUsersByChatId(Long id) {
        return userRepository.findUsersByChatId(id)
                .stream()
                .map(userMapper::mapFrom)
                .collect(Collectors.toList());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                )).orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user : " + username));
    }

}
