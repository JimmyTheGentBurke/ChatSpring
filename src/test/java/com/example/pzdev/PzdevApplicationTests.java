package com.example.pzdev;

import com.example.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PzdevApplicationTests {

    @Test
    void checkManyToMany() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            var user = session.get(User.class, 10L);
            user.getChats().clear();

//            var chat = Chat.builder()
//                    .name("dmdev")
//                    .build();
//            user.addChat(chat);
//
//            session.save(chat);

            session.getTransaction().commit();
        }
    }

}
