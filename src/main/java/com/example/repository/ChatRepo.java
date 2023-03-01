package com.example.repository;

import com.example.entity.Chat;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatRepo extends JpaRepository<Chat, Long> {

    Chat create(Chat entity);
    List<Chat> findChatsByUserCreator(User entity);
    List<Chat> findChatsByRecipient(List<User> entity);



}
