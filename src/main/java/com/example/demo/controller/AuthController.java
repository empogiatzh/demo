package com.example.demo.controller;


import com.example.demo.dto.BookDto;
import com.example.demo.mappers.BookMapper;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class AuthController {


    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;


    public AuthController(UserRepository repo, PasswordEncoder encoder, AuthenticationManager authManager) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
    }


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (repo.existsByUsername(user.getUsername())) {
            return "Username already exists";
        }


//        user.setPassword(encoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("USER"));
        }
        else {
            user.setRoles(user.getRoles());
        }
        repo.save(user);
        return "User registered";
    }


    @PostMapping("/login")
    public String login(@RequestBody User user) {
        var authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authManager.authenticate(authToken);
        return "Login successful (use Basic Auth for future requests)";
    }
}