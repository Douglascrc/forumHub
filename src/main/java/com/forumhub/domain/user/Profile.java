package com.forumhub.domain.user;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Profile")
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "profiles")
    private List<User> users = new ArrayList<>();
}
