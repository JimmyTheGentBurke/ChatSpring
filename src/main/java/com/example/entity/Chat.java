package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"messages", "users"})
@Builder
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User userCreator;
    @OneToMany
    private List<User> recipient;
    @Column(nullable = false,unique = true)
    private String name;

    @OneToMany(mappedBy = "chatId")
    private List<Message> messages = new ArrayList<>();
    @Builder.Default
    @ManyToMany(mappedBy = "chats")
    private List<User> users = new ArrayList<>();

    public void addChat(User user) {
        users.add(user);
        user.getChats().add(this);
    }

}
