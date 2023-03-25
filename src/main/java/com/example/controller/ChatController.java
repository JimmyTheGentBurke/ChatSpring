package com.example.controller;

import com.example.dto.CreateChatDto;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.ChatService;
import com.example.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {

    private final UserService userService;
    private final ChatService chatService;

    @GetMapping()
    public String getChats(@AuthenticationPrincipal UserDetails userDetails,
                                   Model model) {

        model.addAttribute("chats", chatService.findByUserId(userService.findByUsername(
                        userDetails
                                .getUsername())
                                .orElseThrow()
                                .getId()));

        model.addAttribute("user", userService.findByUsername(userDetails.getUsername()));

        return "chat";
    }

    @PostMapping("/v1")
    public String createChat(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam("chatName") String chatName,
                             @RequestParam("recipientName") String recipientName,
                             RedirectAttributes redirectAttributes) {

        Optional<UserDto> recipient = userService.findByNickname(recipientName);

        if (recipient.isEmpty()) {

            redirectAttributes.addFlashAttribute("errorsParam", "There is no users with this nickname");
            return "redirect:/chat";
        }

        return "redirect:/message?chatId=" + chatService.save(
                CreateChatDto
                        .builder()
                        .creator(new User()
                                        .setId(userService.findByUsername(userDetails.getUsername())
                                        .orElseThrow()
                                        .getId()))
                        .name(chatName)
                        .build(), recipient.orElseThrow().getId()).getId();
    }

    @PostMapping("/v2")
    public String addUserToChat(@RequestParam("username") String username,
                                @RequestParam("chatId") Long chatId,
                                RedirectAttributes redirectAttributes) {

        Optional<UserDto> user = userService.findByNickname(username);

        if (user.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorsParam", "There is no users with this nickname");

            return "redirect:/message?chatId=" + chatId;
        }

        return "redirect:/message?chatId=" + chatService.addUser(chatId,
                user.orElseThrow().getId()).getChat();
    }

    @PostMapping("/v3")
    public String createChatFromSearch(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestParam("chatName") String chatName,
                                       @RequestParam("userId") Long userId) {

        return "redirect:/message?chatId=" + chatService.save(
                CreateChatDto
                .builder()
                .creator(new User().setId(userService
                                   .findByUsername(userDetails
                                   .getUsername())
                                   .orElseThrow().getId()))
                .name(chatName)
                .build(), userId).getId();
    }

    @PostMapping("/v4")
    public String getUserByNickname(@RequestParam @NotNull String nickname,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {

        Optional<UserDto> UserByNickname = userService.findByNickname(nickname);

        if (UserByNickname.isEmpty()) {

            redirectAttributes.addFlashAttribute("errorsParam", "There is no users with this nickname");

            return "redirect:/chat";
        }

        model.addAttribute("searchByNickName",UserByNickname);

        return "chat";
    }

    @PostMapping("/v5")
    public String deleteChat(@RequestParam("chatId") Long chatId) {

        chatService.delete(chatId);
        return "redirect:/chat";

    }

    @PostMapping("/v6")
    public String deleteUser(@RequestParam("recipientId") Long recipientId,
                             @RequestParam("chatId") Long chatId) {

        chatService.deleteUser(recipientId, chatId);

        if (chatService.countUsersInChat(chatId) == 1) {

            chatService.delete(chatId);
            return "redirect:/chat";
        }

        return "redirect:/message?chatId=" + chatId;
    }

}
