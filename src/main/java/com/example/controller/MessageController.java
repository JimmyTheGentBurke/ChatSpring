package com.example.controller;

import com.example.dto.ChatDto;
import com.example.dto.CreateMessageDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;

    @GetMapping
    @SneakyThrows
    public String getMessagesByChatId(Model model,
                                      @RequestParam("chatId") Long chatId) {

        model.addAttribute("messages", messageService.findByChatId(chatId));
        model.addAttribute("recipients", userService.findUsersByChatId(chatId));
        model.addAttribute("ChatId", chatId);

        return "forward:/chat";
    }

    @PostMapping
    @SneakyThrows
    public String createMessage(@AuthenticationPrincipal UserDetails userDetails,
                                HttpServletRequest request,
                                @RequestParam("chatId") Long chatId) {

        Optional<ChatDto> chatDto = chatService.findById(chatId);

        Chat chat = Chat.builder()
                .id(chatDto.orElseThrow().getId())
                .creator(chatDto.orElseThrow().getCreator())
                .name(chatDto.orElseThrow().getName())
                .build();

        messageService.create(CreateMessageDto.builder()
                .text(request.getParameter("textMessage"))
                .chatId(chat)
                .creatorId(userService.findByUsername(userDetails.getUsername()).orElseThrow().getId())
                .build());

        return "redirect:/message?chatId=" + chatId;
    }

}
