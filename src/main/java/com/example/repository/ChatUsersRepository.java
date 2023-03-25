package com.example.repository;

import com.example.entity.ChatUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ChatUsersRepository extends JpaRepository<ChatUsers, Long> {

    void deleteChatUsersByUserAndChat(Long userId, Long chatId);

    Long countChatUsersByChat(Long id);


}
