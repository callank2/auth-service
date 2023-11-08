package com.kevin.auth.api;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kevin.auth.domain.dto.UserRegistrationDTO;
import com.kevin.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserRegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registrationPage(Model model){
        return "createUser";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerUser(Model model,
                               UserRegistrationDTO userDTO) {
        if(!userService.registerUser(userDTO)) return usernameAlreadyExists(model);
        model.addAttribute("username", userDTO.username());
        model.addAttribute("isNewUser", true);
        return "login";
    }

    private String usernameAlreadyExists(Model model){
        model.addAttribute("invalidUsername", true);
        return "createUser";
    }
}
