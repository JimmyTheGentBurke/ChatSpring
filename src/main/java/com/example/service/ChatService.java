package com.example.service;

import com.example.dto.ChatDto;
import com.example.dto.CreateChatDto;
import com.example.entity.Chat;
import com.example.entity.ChatUsers;
import com.example.mapper.ChatMapper;
import com.example.repository.ChatRepository;
import com.example.repository.ChatUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatUsersRepository chatUsersRepository;
    private final ChatMapper chatMapper;

    @Transactional
    public Optional<Chat> save(CreateChatDto createChatDto, Long idRecipient) {

        Chat chat = chatRepository.save(chatMapper.createChatMapper(createChatDto));

        chatUsersRepository.save(ChatUsers.builder()
                .user(chat.getCreator().getId())
                .chat(chat.getId())
                .build());

        chatUsersRepository.save(ChatUsers.builder()
                .user(idRecipient)
                .chat(chat.getId())
                .build());

        return Optional.of(chat);

    }
    @Transactional
    public Optional<ChatUsers> addUser(Long chatId, Long userId) {

        return Optional.of(chatUsersRepository.save(ChatUsers.builder()
                .chat(chatId)
                .user(userId)
                .build()));

    }
    @Transactional(readOnly = true)
    public List<ChatDto> findByUserId(Long id) {
        return chatRepository.findChatsByUser(id).stream()
                .map(chatMapper::mapFrom)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Optional<ChatDto> findById(Long id) {
        return Optional.of(chatRepository.findById(id)
                .map(chatMapper::mapFrom)
                .orElseThrow());
    }

}
