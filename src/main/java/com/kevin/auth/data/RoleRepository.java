package com.kevin.auth.data;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.kevin.auth.domain.Role;

public interface RoleRepository extends Neo4jRepository<Role, String> {

    Optional<Role> findByName(String name);

    void deleteAllByName(String name);
}
