package com.kevin.auth;

import static com.kevin.auth.domain.constants.Privileges.PRIVILEGE_LIST_USERS;
import static com.kevin.auth.domain.constants.Roles.ROLE_ADMIN;
import static com.kevin.auth.domain.constants.Roles.ROLE_USER;
import static java.util.Collections.EMPTY_LIST;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kevin.auth.data.PrivilegeRepository;
import com.kevin.auth.data.RoleRepository;
import com.kevin.auth.data.UserRepository;
import com.kevin.auth.domain.Privilege;
import com.kevin.auth.domain.Role;
import com.kevin.auth.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SetupAdminLoader implements ApplicationListener<ContextRefreshedEvent> {

    // This is dummy data for testing
    private static final String ADMIN_USERNAME = "AdminUser";
    private static final String ADMIN_PASSWORD = "password";
    private static final String ADMIN_EMAIL = "test@test.com";

    boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        Privilege listUsersPrivilege
                = createPrivilegeIfNotFound(PRIVILEGE_LIST_USERS);

        List<Privilege> adminPrivileges = List.of(listUsersPrivilege);
        Role adminRole = createRoleIfNotFound(ROLE_ADMIN, adminPrivileges);
        createRoleIfNotFound(ROLE_USER, EMPTY_LIST);

        createAdminUserIfNotFound(Set.of(adminRole));

        alreadySetup = true;
    }

    User createAdminUserIfNotFound(Set<Role> adminRoles){
        return userRepository.findByUsername(ADMIN_USERNAME).orElse(
                userRepository.save(User.builder()
                        .email(ADMIN_EMAIL)
                        .password(bCryptPasswordEncoder.encode(ADMIN_PASSWORD))
                        .username(ADMIN_USERNAME)
                        .roles(adminRoles)
                        .build())
        );
    }

    Privilege createPrivilegeIfNotFound(String name) {
        return privilegeRepository.findByName(name).orElse(
                privilegeRepository.save(
                        new Privilege(name, 0)
                )
        );
    }

    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {
        return roleRepository.findByName(name).orElse(
                roleRepository.save(
                        new Role(name, 0, privileges)
                )
        );
    }
}
