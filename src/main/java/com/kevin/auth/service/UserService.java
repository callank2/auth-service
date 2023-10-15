package com.kevin.auth.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevin.auth.data.UserRepository;
import com.kevin.auth.domain.User;
import com.kevin.auth.domain.dto.UserRegistrationDTO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    @Transactional
    public boolean registerUser(UserRegistrationDTO dto){
        boolean userExistsWithUsername = userRepository.existsByUsername(dto.username());
        if(userExistsWithUsername)
            return false;

        User user = User.builder()
                .id(UUID.randomUUID())
                .username(dto.username())
                .password(bCryptPasswordEncoder.encode(dto.password()))
                .email(dto.email())
                .build();
        userRepository.save(user);
        return true;
    }
}
