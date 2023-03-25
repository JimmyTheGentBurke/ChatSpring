package com.example.service;

import com.example.dto.CreateMessageDto;
import com.example.entity.Message;
import com.example.mapper.MessageMapper;
import com.example.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void create(CreateMessageDto messageDto) {
        messageRepository.save(messageMapper.mapFrom(messageDto));
    }

    @Transactional
    public void delete(Long id){
        messageRepository.deleteById(id);
    }

}
