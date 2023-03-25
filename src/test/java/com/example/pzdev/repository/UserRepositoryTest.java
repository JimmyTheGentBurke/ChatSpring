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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest extends IntegrationTestBase {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatUsersRepository chatUsersRepository;

    @Test
    void findUsersByChatIdTest() {
        //given
        User user = userRepository.save(UserGenerator.createUser());
        User user2 = userRepository.save(UserGenerator.createUser());
        User user3 = userRepository.save(UserGenerator.createUser());

        Chat chat = chatRepository.save(ChatGenerator.createChat(user));

        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat.getId(), user.getId()));
        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat.getId(), user2.getId()));
        chatUsersRepository.save(ChatUsersGenerator.chatUserCreate(chat.getId(), user3.getId()));

        //when
        List<User> chatsByUser = userRepository.findUsersByChatId(chat.getId());

        //then
        assertThat(chatsByUser).hasSize(3);
    }

    @Test
    void findByNicknameIgnoreCaseTest() {

        User user = userRepository.save(UserGenerator.createUser().setNickname("Boris"));

        String firstCase = "BoRiS";
        String secondCase = "BORIS";
        String thirdCase = "boris";

        Optional<User> byNicknameIgnoreCase = userRepository.findByNicknameIgnoreCase(firstCase);
        Optional<User> byNicknameIgnoreCase1 = userRepository.findByNicknameIgnoreCase(secondCase);
        Optional<User> byNicknameIgnoreCase2 = userRepository.findByNicknameIgnoreCase(thirdCase);

        assertThat(byNicknameIgnoreCase).isPresent();
        assertThat(byNicknameIgnoreCase.get().getNickname()).isEqualTo(user.getNickname());
        assertThat(byNicknameIgnoreCase1).isPresent();
        assertThat(byNicknameIgnoreCase.get().getNickname()).isEqualTo(user.getNickname());
        assertThat(byNicknameIgnoreCase2).isPresent();
        assertThat(byNicknameIgnoreCase.get().getNickname()).isEqualTo(user.getNickname());
    }

    @Test
    void findByUsernameTest() {

        User user = UserGenerator.createUser();
        user.setUsername("Boris");
        userRepository.save(user);
        String firstCase = "Boris";

        Optional<User> byNicknameIgnoreCase = userRepository.findByUsername(firstCase);

        assertThat(byNicknameIgnoreCase).isPresent();
        assertThat(byNicknameIgnoreCase.get().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void findUserByTest() {
        User user = UserGenerator.createUser();
        userRepository.save(user);

        User userById = userRepository.findUserById(user.getId());
        assertThat(userById).isNotNull();
    }

}
