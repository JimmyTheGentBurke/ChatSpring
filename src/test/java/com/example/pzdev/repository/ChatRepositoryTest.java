package com.example.pzdev.repository;

import com.example.entity.Chat;
import com.example.entity.User;
import com.example.pzdev.integration.IntegrationTestBase;
import com.example.pzdev.util.ChatGenerator;
import com.example.pzdev.util.ChatUsersGenerator;
import com.example.pzdev.util.UserGenerator;
import com.example.repository.ChatRepository;
import com.example.repository.ChatUsersRepository;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class ChatRepositoryTest extends IntegrationTestBase {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatUsersRepository chatUsersRepository;



    @Test
    void findChatsByUserTest() {
        //given
        User user1 = userRepository.save(UserGenerator.createUser());

        Chat chat1 = chatRepository.save(ChatGenerator.createChat(user1));
        Chat chat2 = chatRepository.save(ChatGenerator.createChat(user1));
        Chat chat3 = chatRepository.save(ChatGenerator.createChat(user1));

        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat1.getId(), user1.getId()));
        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat2.getId(), user1.getId()));
        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat3.getId(), user1.getId()));

        //when
        List<Chat> chatsByUser = chatRepository.findChatsByUser(user1.getId());

        //then
        assertThat(chatsByUser).hasSize(3);
        chatsByUser.forEach(e -> assertThat(e.getCreator().getId()).isEqualTo(user1.getId()));
    }


}
