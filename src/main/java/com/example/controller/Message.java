package com.example.controller;

import com.example.dto.ChatDto;
import com.example.dto.CreateMessageDto;
import com.example.dto.UserDto;
import com.example.entity.Chat;
import com.example.service.ChatService;
import com.example.service.MessageService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/message")
@AllArgsConstructor
public class Message {

    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;
    @GetMapping
    @SneakyThrows
    public String getMessagesByChatId(Model model,
                                      @RequestParam("chatId") Long chatId) {

        List<com.example.entity.Message> messages = messageService.findByChatId(chatId);
        List<UserDto> recipients = userService.findUsersByChatId(chatId);

        model.addAttribute("messages", messages);
        model.addAttribute("recipients", recipients);
        model.addAttribute("ChatId", chatId);

        return "forward:/chat";
    }

    @PostMapping
    @SneakyThrows
    public String createMessage(@AuthenticationPrincipal UserDetails userDetails,
                                HttpServletRequest request,
                                @RequestParam("chatId") Long chatId) {

        Optional<UserDto> authorisedUser = userService.findByUsername(userDetails.getUsername());

        Optional<ChatDto> chatDto = chatService.findById(chatId);

        Chat chat = Chat.builder()
                .id(chatDto.orElseThrow().getId())
                .creator(chatDto.orElseThrow().getCreator())
                .name(chatDto.orElseThrow().getName())
                .build();

        messageService.create(CreateMessageDto.builder()
                .text(request.getParameter("textMessage"))
                .chatId(chat)
                .creatorId(authorisedUser.orElseThrow().getId())
                .build());

        return "redirect:/message?chatId=" + chatId;
    }

}
