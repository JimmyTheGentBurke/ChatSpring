package com.example.controller;

import com.example.dto.ChatDto;
import com.example.dto.CreateChatDto;
import com.example.dto.CreateMessageDto;
import com.example.dto.UserDto;
import com.example.entity.ChatUsers;
import com.example.entity.User;
import com.example.service.ChatService;
import com.example.service.MessageService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/chat")
@AllArgsConstructor
public class Chat {

    private final UserService userService;
    private final ChatService chatService;

    @GetMapping()
    public String chatInfo(@AuthenticationPrincipal UserDetails userDetails,
                           Model model) {

        Optional<UserDto> authorisedUser = userService.findByUsername(userDetails.getUsername());

        List<ChatDto> chats = chatService.findByUserId(authorisedUser.orElseThrow().getId());

        model.addAttribute("chats", chats);
        model.addAttribute("user", authorisedUser);
        model.addAttribute("users", userService.findAll());




        return "/chat";
    }

    @PostMapping()
    @SneakyThrows
    public String createChat(@AuthenticationPrincipal UserDetails userDetails,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam("chatIdForAddUser") Long chatIdForAddUser,
                             @RequestParam("UserName") Long UserName,
                             @RequestParam("userIdCreateSearch") Long userIdCreateSearch) {

        Optional<UserDto> authorisedUser = userService.findByUsername(userDetails.getUsername());

        if (request.getParameter("recipientName") != null) {

            Optional<UserDto> recipient = userService.findByNickName(request.getParameter("recipientName"));

            Optional<com.example.entity.Chat> chat = chatService.save(CreateChatDto.builder()
                    .creator(new User().setId(authorisedUser.orElseThrow().getId()))
                    .name(request.getParameter("chatName"))
                    .build(), recipient.orElseThrow().getId());

            response.sendRedirect("/message?chatId=" + chat.orElseThrow().getId());

        } else if (request.getParameter("UserName") != null) {

            Optional<ChatUsers> chatUsers = chatService.addUser(chatIdForAddUser, UserName);

            response.sendRedirect("/message?chatId=" + chatUsers.orElseThrow().getChat());

        } else {

            CreateChatDto createChatFromSearch = CreateChatDto.builder()
                    .creator(new User().setId(authorisedUser.orElseThrow().getId()))
                    .name(request.getParameter("createChatFromSearch"))
                    .build();

            Optional<com.example.entity.Chat> chat = chatService.save(createChatFromSearch, userIdCreateSearch);
            response.sendRedirect("/message?chatId=" + chat.orElseThrow().getId());

        }

        return "chat";
    }

}
