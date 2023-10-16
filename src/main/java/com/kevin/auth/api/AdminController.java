package com.kevin.auth.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kevin.auth.domain.dto.ListUserDTO;
import com.kevin.auth.service.ListUsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final ListUsersService listUsersService;

    @GetMapping("/listUsers")
    public String registrationPage(Model model){
        List<ListUserDTO> users = listUsersService.listUsers();
        model.addAttribute("users", users);
        return "listUsers";
    }
}
