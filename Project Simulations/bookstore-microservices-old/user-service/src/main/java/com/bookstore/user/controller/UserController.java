package com.bookstore.user.controller;

import com.bookstore.security.JwtService;
import com.bookstore.user.entity.*;
import com.bookstore.user.repository.CustomerDetailsRepository;
import com.bookstore.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bookstore_user")
public class UserController {
    private final UserService service;
    private final CustomerDetailsRepository customerRepo;

    public UserController(UserService service, CustomerDetailsRepository customerRepo){
        this.service=service; this.customerRepo=customerRepo;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        return ResponseEntity.ok(service.register(user, Role.USER));
    }

    @PostMapping("/admin/registration")
    public ResponseEntity<?> adminRegister(@RequestBody User user){
        return ResponseEntity.ok(service.register(user, Role.ADMIN));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        return ResponseEntity.ok(Map.of("message","Sucessfully loged in",
                "token",service.login(body.get("email"),body.get("password"))));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String,String> body){
        return ResponseEntity.ok(Map.of("message","Sucessfully loged in",
                "token",service.login(body.get("email"),body.get("password"))));
    }

    @PostMapping("/verification/{token}")
    public ResponseEntity<?> verify(@PathVariable String token){
        return ResponseEntity.ok(Map.of("message","Verified successfully"));
    }


    @PutMapping("/edit_user")
    public ResponseEntity<?> editUser(@RequestBody CustomerDetails details, Authentication auth){
        details.setEmail(auth.getName());
        return ResponseEntity.ok(customerRepo.save(details));
    }
}
