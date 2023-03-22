package com.seChat.artifacts;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;


@Document(collection = "roles")
public class Role implements GrantedAuthority {

    @Id
    private String id;
    ERole name;

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.getName().name();
    }
}