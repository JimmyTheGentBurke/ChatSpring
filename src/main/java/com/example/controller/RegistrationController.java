package com.example.controller;

import com.example.dto.CreateUserDto;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;


    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "index";
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute CreateUserDto createUserDto) {

        userService.create(createUserDto);
        return "index";
    }

}
