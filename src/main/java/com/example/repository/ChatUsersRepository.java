package com.example.repository;

import com.example.entity.ChatUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUsersRepository extends JpaRepository<ChatUsers, Long> {

}
