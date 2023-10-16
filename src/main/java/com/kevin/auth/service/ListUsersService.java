package com.kevin.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kevin.auth.data.UserRepository;
import com.kevin.auth.domain.User;
import com.kevin.auth.domain.dto.ListUserDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ListUsersService {

    private final UserRepository userRepository;

    public List<ListUserDTO> listUsers(){
        return userRepository.findAll()
                .stream()
                .map(u -> new ListUserDTO(u.getUsername(), u.getEmail()))
                .collect(Collectors.toList());
    }
}
