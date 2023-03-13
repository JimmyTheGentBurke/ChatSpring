package com.example.controller;

import com.example.dto.CreateUserDto;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;


    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "index";
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute @Validated CreateUserDto createUserDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("user", createUserDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/registration";
        }

        userService.create(createUserDto);
        return "index";
    }

}
