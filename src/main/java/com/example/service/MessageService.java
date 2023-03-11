package com.example.service;

import com.example.dto.CreateMessageDto;
import com.example.entity.Message;
import com.example.mapper.MessageMapper;
import com.example.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Transactional(readOnly = true)
    public List<Message> findByChatId(Long id) {
        return messageRepository.findByChatId(id);
    }

    @Transactional
    public Optional<Message> create(CreateMessageDto messageDto) {
        return Optional.of(messageRepository.save(messageMapper.mapFrom(messageDto)));
    }

}
