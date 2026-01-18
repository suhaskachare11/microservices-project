package com.example.user.controller;

import com.example.user.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("User Service: /hello called");
        return "Hello from User Service";
    }

    @GetMapping("/all")
    public Object getAll() {
        System.out.println("User Service: fetching users from DB");
        return repo.findAll();
    }
}

