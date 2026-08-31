package com.bookstore.user.service;

import com.bookstore.security.JwtService;
import com.bookstore.user.entity.Role;
import com.bookstore.user.entity.User;
import com.bookstore.user.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;
    private final RabbitTemplate rabbit;

    public UserService(UserRepository repo, PasswordEncoder encoder, JwtService jwt, RabbitTemplate rabbit) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
        this.rabbit = rabbit;
    }

    public User register(User user, Role role) {
        if (repo.existsByEmail(user.getEmail())) throw new IllegalArgumentException("Email already registered");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(role);
        user.setVerified(true);
        User saved = repo.save(user);
        rabbit.convertAndSend("bookstore.user.events",
                Map.of("event", "USER_REGISTERED", "email", saved.getEmail(), "role", saved.getRole().name()));
        saved.setPassword(null);
        return saved;
    }

    public String login(String email, String password) {
        User user = repo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!encoder.matches(password, user.getPassword()))
            throw new IllegalArgumentException("Invalid email or password");
        return jwt.generateToken(user.getEmail(), user.getRole().name());
    }
}
