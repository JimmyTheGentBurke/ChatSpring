package com.example.controller;

import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String personalPage(@AuthenticationPrincipal UserDetails userDetails,
                            Model model) {

        model.addAttribute("user", userService.findByUsername(userDetails.getUsername()));
        return "personal_page";
    }

    @PostMapping("/v1")
    public String updatedNickname(@AuthenticationPrincipal UserDetails userDetails,
                                  @RequestParam("nickname") String updatedNickname) {

        userService.updateUsername(userDetails.getUsername(), updatedNickname);
        return "redirect:/user";
    }

    @PostMapping("/v2")
    public String updatePassword(@AuthenticationPrincipal UserDetails userDetails,
                                 @RequestParam("password") String rawPassword) {

        userService.updatePassword(userDetails.getUsername(), rawPassword);
        return "redirect:/user";
    }

    @PostMapping("/v3")
    public String deleteUser(@AuthenticationPrincipal UserDetails userDetails) {

        userService.deleteUser(userService.findByUsername(userDetails.getUsername()).orElseThrow().getId());
        return "redirect:/login";
    }

}
