//package com.example.pzdev;
//
//import com.example.entity.Chat;
//import com.example.entity.ChatUsers;
//import com.example.entity.User;
//import com.example.pzdev.util.DataGenerator;
//import com.example.repository.ChatRepository;
//import com.example.repository.ChatUsersRepository;
//import com.example.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Random;
//
//@SpringBootTest
//@RequiredArgsConstructor
//class PzdevApplicationTests {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ChatRepository chatRepository;
//    @Autowired
//    private ChatUsersRepository chatUsersRepository;
//
//
//
//  /*  @BeforeEach
//    void beforeEach() {
//        userRepository.deleteAll();
//        chatRepository.deleteAll();
//    }*/
//
//    @Test
//    void test() {
//
//        Assertions.assertThat()
//        //given
//        List<User> users = userRepository.saveAll(DataGenerator.generateUsers(50));
//        List<Chat> chats = chatRepository.saveAll(DataGenerator.generateChats(50, users));
//
//        User user = users.get(0);
//
//        //when
////        List<Chat> chatsByUserCreator = chatRepository.findChatsByUserCreator(user);
//
////        chatsByUserCreator.forEach(e -> e.getName());
//    }
//
//    @Test
//    void test2() {
//
//        List<User> usersByChatId = userRepository.findUsersByChatId(33L);
//        List<Chat> chatsByUser = chatRepository.findChatsByUser(1L);
//        List<Chat> chatsByUser2 = chatRepository.findChatsByUser(1L);
//
//
//
//    }
//}


