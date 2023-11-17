package com.jasper.pigrakker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;
import java.util.Set;


@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Gebruikersnaam mag niet leeg zijn.")
    @Column(nullable = false, unique = true)
    private String name;

    @NotEmpty(message = "Email mag niet leeg zijn.")
    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String imageUrl;


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}