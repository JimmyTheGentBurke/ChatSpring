//package com.example.pzdev.it.controller;
//
//
//import com.example.entity.Role;
//import com.example.entity.User;
//import com.example.pzdev.config.TestWebSecurityConfig;
//import com.example.pzdev.integration.IntegrationTestBase;
//import com.example.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ActiveProfiles("test")
//@SpringBootTest
//@Import(TestWebSecurityConfig.class)
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//public class LoginControllerTest extends IntegrationTestBase {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserRepository userRepository;
//    private User user;
//
//    @BeforeEach
//    public void beforeEach() {
//        user = userRepository.save(User.builder()
//                .username("Ludicrous")
//                .password("Ludicrous")
//                .nickName("Ludicrous")
//                .role(Role.USER)
//                .build());
//    }
//
//    @Test
//    void authTest() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .request(HttpMethod.POST, "/login")
//                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .param("username", user.getUsername())
//                        .param("password", user.getPassword())
//                        .accept(MediaType.APPLICATION_FORM_URLENCODED)
//                ).andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/chat"))
//                .andExpect(xpath("//*[@class=\"content\"]").exists());
//    }
//
//
//}
