package com.forumhub.controller;

import com.forumhub.dto.AuthResponse;
import com.forumhub.dto.LoginRequest;
import com.forumhub.dto.RegisterRequest;
import com.forumhub.entity.User;
import com.forumhub.service.AuthService;
import com.forumhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            
            if (token != null) {
                User user = userService.findByUsername(loginRequest.getUsername()).get();
                AuthResponse response = new AuthResponse(token, user.getUsername(), user.getEmail());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("Credenciais inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no login: " + e.getMessage());
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            User user = userService.createUser(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword()
            );
            
            return ResponseEntity.ok("Usuário registrado com sucesso! ID: " + user.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro no registro: " + e.getMessage());
        }
    }
}