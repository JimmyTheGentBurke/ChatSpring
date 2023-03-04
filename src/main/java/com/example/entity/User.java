package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "nickName")
@ToString(exclude = {"chats"})
@Builder
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String username;
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "chat_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private List<Chat> chats = new ArrayList<>();


    public void addChat(Chat chat) {
        chats.add(chat);
        chat.getUsers().add(this);
    }

}