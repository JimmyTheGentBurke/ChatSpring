package com.example.pzdev.repository;

import com.example.entity.Chat;
import com.example.entity.Message;
import com.example.entity.User;
import com.example.pzdev.integration.IntegrationTestBase;
import com.example.pzdev.util.ChatGenerator;
import com.example.pzdev.util.MessageGenerator;
import com.example.pzdev.util.UserGenerator;
import com.example.repository.ChatRepository;
import com.example.repository.MessageRepository;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class MessageRepositoryTest  extends IntegrationTestBase {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;


    @Test
    void findByChatIdTest() {

        User user = UserGenerator.createUser();
        userRepository.save(user);

        Chat chat = ChatGenerator.createChat(user);
        chatRepository.save(chat);

        Message message = MessageGenerator.CreateMessages(user, chat);
        messageRepository.save(message);
        Message message2 = MessageGenerator.CreateMessages(user, chat);
        messageRepository.save(message2);
        Message message3 = MessageGenerator.CreateMessages(user, chat);
        messageRepository.save(message3);

        List<Message> byChatId = messageRepository.findByChatId(chat.getId());

        assertThat(byChatId).hasSize(3);
        byChatId.forEach(e -> assertThat(e.getCreator().getId()).isEqualTo(user.getId()));
    }


}


