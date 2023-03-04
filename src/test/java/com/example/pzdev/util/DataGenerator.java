//package com.example.pzdev.util;
//
//import com.example.entity.Chat;
//import com.example.entity.User;
//import lombok.experimental.UtilityClass;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@UtilityClass
//public class DataGenerator {
//
//    public List<User> generateUsers(int count) {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            users.add(User.builder()
//                    .username("testLogin" + UUID.randomUUID())
//                    .nickName("testLogin" + UUID.randomUUID())
//                    .password("testPassword" + UUID.randomUUID())
//                    .build());
//        }
//
//        return users;
//    }
//
//    public List<Chat> generateChats(int count, List<User> users) {
//        List<Chat> chats = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            chats.add(Chat.builder()
//                    .name("testChat" + UUID.randomUUID())
//                    .creator(users.get(0))
//                    .build());
//        }
//        return chats;
//    }
//
//
//}
