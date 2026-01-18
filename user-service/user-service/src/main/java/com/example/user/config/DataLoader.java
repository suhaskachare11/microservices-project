package com.example.user.config;

import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository repo) {
        return args -> {
            repo.save(new User("Suhas"));
            repo.save(new User("Amit"));
            System.out.println("Sample users inserted...");
        };
    }
}

