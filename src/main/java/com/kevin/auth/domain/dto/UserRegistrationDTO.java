package com.kevin.auth.domain.dto;

public record UserRegistrationDTO(
        String username,
        String password,
        String email
){}
