package com.example.repository;

import com.example.entity.Chat;
import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    Message create(Message entity);
    List<Message> findALl();
    List<Message> findByChatId(Chat entity);

}
