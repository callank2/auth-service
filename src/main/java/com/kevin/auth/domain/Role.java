package com.kevin.auth.domain;

import java.util.Collection;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Node("Role")
public class Role {

    @Id private String name;

    @Relationship(type = "HAS_PRIVILEGE", direction = Relationship.Direction.OUTGOING)
    private Collection<Privilege> privileges;
}
