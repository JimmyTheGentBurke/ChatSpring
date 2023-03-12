package com.example.controller;

import com.example.dto.CreateChatDto;
import com.example.dto.UserDto;
import com.example.entity.ChatUsers;
import com.example.entity.User;
import com.example.service.ChatService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {

    private final UserService userService;
    private final ChatService chatService;

    @GetMapping()
    public String getChatsByUserId(@AuthenticationPrincipal UserDetails userDetails,
                                   HttpServletRequest request,
                                   Model model) {

        Optional<UserDto> authorisedUser = userService.findByUsername(userDetails.getUsername());

        model.addAttribute("chats", chatService.findByUserId(authorisedUser.orElseThrow().getId()));
        model.addAttribute("user", authorisedUser);
        model.addAttribute("users", userService.findAll());

        if (request.getParameter("nickNameFromSearch") != null) {
            model.addAttribute("searchByNickName",
                    userService.findByNickName(request.getParameter("nickNameFromSearch")));

        }

        return "chat";
    }

    @PostMapping("/v1")
    public String createChat(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam("chatName") String chatName,
                             @RequestParam("recipientName") String recipientName) {

        Optional<UserDto> authorisedUser = userService.findByUsername(userDetails.getUsername());

        Optional<UserDto> recipient = userService.findByNickName(recipientName);

        Optional<com.example.entity.Chat> chat = chatService.save(CreateChatDto.builder()
                .creator(new User().setId(authorisedUser.orElseThrow().getId()))
                .name(chatName)
                .build(), recipient.orElseThrow().getId());

        return "redirect:/chat?chatId=" + chat.orElseThrow().getId();
    }

    @PostMapping("/v2")
    public String addUserToChat(@RequestParam("UserName") String UserName,
                                @RequestParam("chatIdForAddUser") Long chatIdForAddUser) {

        Optional<UserDto> userName = userService.findByNickName(UserName);

        Optional<ChatUsers> chatUsers = chatService.addUser(chatIdForAddUser,
                userName.orElseThrow().getId());

        return "redirect:/message?chatId=" + chatUsers.orElseThrow().getChat();
    }

    @PostMapping("/v3")
    public String createChatFromSearch(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestParam("chatName") String chatName,
                                       @RequestParam("userIdCreateSearch") Long userIdCreateSearch) {

        Optional<UserDto> authorisedUser = userService.findByUsername(userDetails.getUsername());

        CreateChatDto createChatFromSearch = CreateChatDto.builder()
                .creator(new User().setId(authorisedUser.orElseThrow().getId()))
                .name(chatName)
                .build();

        Optional<com.example.entity.Chat> chat = chatService.save(createChatFromSearch, userIdCreateSearch);

        return "redirect:/message?chatId=" + chat.orElseThrow().getId();
    }

}
