package com.example.repository;

import com.example.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT с FROM Chat с JOIN ChatUsers cu ON с.id = cu.chat WHERE cu.user = :id")
    List<Chat> findChatsByUser(Long id);


}
