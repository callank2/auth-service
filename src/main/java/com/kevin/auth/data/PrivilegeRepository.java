package com.kevin.auth.data;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.kevin.auth.domain.Privilege;

public interface PrivilegeRepository extends Neo4jRepository<Privilege, String> {

    Optional<Privilege> findByName(String name);

    void deleteAllByName(String name);
}
