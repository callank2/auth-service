package com.kevin.auth.service;

import static com.kevin.auth.domain.constants.Roles.ROLE_USER;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kevin.auth.data.RoleRepository;
import com.kevin.auth.data.UserRepository;
import com.kevin.auth.domain.Role;
import com.kevin.auth.domain.User;
import com.kevin.auth.domain.dto.UserRegistrationDTO;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
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
                .username(dto.username())
                .password(bCryptPasswordEncoder.encode(dto.password()))
                .email(dto.email())
                .roles(getUserRoles())
                .build();
        userRepository.save(user);
        return true;
    }

    private Set<Role> getUserRoles(){
        return Set.of(roleRepository.findByName(ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("The USER role does not exist in the DB")));
    }
}
