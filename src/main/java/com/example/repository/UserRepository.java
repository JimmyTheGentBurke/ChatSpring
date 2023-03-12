package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN ChatUsers cu ON u.id = cu.user WHERE cu.chat = :id")
    List<User> findUsersByChatId(Long id);

    Optional<User> findByNickNameIgnoreCase(String nickName);

    Optional<User> findByUsername(String username);
}
