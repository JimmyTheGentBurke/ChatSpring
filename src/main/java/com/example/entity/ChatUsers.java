package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"chat", "user"})
@ToString
@Builder
@Entity
@Table(name = "chat_users")
@IdClass(ChatUsers.class)
public class ChatUsers implements Serializable {
    @Id
    @Column(name = "chat_id")
    private Long chat;
    @Id
    @Column(name = "user_id")
    private Long user;

}
