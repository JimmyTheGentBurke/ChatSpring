package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
@AllArgsConstructor

@Builder
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private Chat chatId;
    @ManyToOne
    private User creatorId;

}