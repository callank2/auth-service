package com.kevin.auth.data;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.kevin.auth.domain.User;

public interface UserRepository extends Neo4jRepository<User, String> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    void deleteAllByUsername(String username);
}
