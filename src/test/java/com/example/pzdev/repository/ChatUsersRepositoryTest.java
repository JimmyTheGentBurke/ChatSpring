package com.example.pzdev.repository;

import com.example.entity.Chat;
import com.example.entity.ChatUsers;
import com.example.entity.User;
import com.example.pzdev.integration.IntegrationTestBase;
import com.example.pzdev.util.ChatGenerator;
import com.example.pzdev.util.ChatUsersGenerator;
import com.example.pzdev.util.UserGenerator;
import com.example.repository.ChatRepository;
import com.example.repository.ChatUsersRepository;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ChatUsersRepositoryTest extends IntegrationTestBase {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatUsersRepository chatUsersRepository;

    @Test
    @Transactional
    void deleteChatUsersByUserAndChatTest() {

        User user = userRepository.save(UserGenerator.createUser());
        User user2 = userRepository.save(UserGenerator.createUser());

        Chat chat = chatRepository.save(ChatGenerator.createChat(user));

        ChatUsers chatUsers = chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat.getId(), user.getId()));
        ChatUsers chatUsers2 = chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat.getId(), user2.getId()));


        chatUsersRepository.deleteChatUsersByUserAndChat(user.getId(), chat.getId());

        List<User> usersByChatId = userRepository.findUsersByChatId(chat.getId());

        assertThat(usersByChatId).hasSize(1);
        usersByChatId.forEach(e -> assertThat(e.getId()).isNotEqualTo(user.getId()));
    }

    @Test
    void countChatUsersByChatTest() {

        User user = UserGenerator.createUser();
        User user2 = UserGenerator.createUser();
        User user3 = UserGenerator.createUser();
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);

        Chat chat = ChatGenerator.createChat(user);
        chatRepository.save(chat);

        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat.getId(), user.getId()));
        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat.getId(), user2.getId()));
        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat.getId(), user3.getId()));

        Long users = chatUsersRepository.countChatUsersByChat(chat.getId());


        assertThat(users).isEqualTo(3);


    }

}
