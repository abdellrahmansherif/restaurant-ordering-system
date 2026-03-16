package org.example.restaurant_system.Services;

import jakarta.validation.Valid;
import org.example.restaurant_system.DTO.LoginRequest;
import org.example.restaurant_system.DTO.RegisterRequest;
import org.example.restaurant_system.DTO.RegisterResponse;
import org.example.restaurant_system.Repositories.UserRepository;
import org.example.restaurant_system.Security.JWTservice;
import org.example.restaurant_system.models.Role;
import org.example.restaurant_system.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTservice jwTservice;

    public RegisterResponse Register(RegisterRequest Requset) {
        if (userRepository.existsByEmail(Requset.email())) {
            throw new IllegalStateException("Email already exist");
        }
        if (Requset.phoneNumber() != null && !Requset.phoneNumber().isBlank()
                && userRepository.existsByPhoneNumber(Requset.phoneNumber())) {
            throw new IllegalStateException("Phone number already exist");
        }
        User user = new User();
        user.setUserName(Requset.userName());
        user.setEmail(Requset.email());
        user.setPassword(Requset.password());
        user.setPhoneNumber(
                Requset.phoneNumber() == null || Requset.phoneNumber().isBlank()
                        ? null
                        : Requset.phoneNumber()
        );

        user.setRole(Role.USER);
        userRepository.save(user);

        RegisterResponse response=new RegisterResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getRole().name()
                );

        return response;
    }

    public String Signin(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
            return jwTservice.generateToken(request.email());
        } catch (Exception e) {
            Throwable root = e;
            while (root.getCause() != null) root = root.getCause();
            return "AUTH FAILED: " + e.getClass().getSimpleName()
                    + " | msg=" + e.getMessage()
                    + " | root=" + root.getClass().getSimpleName()
                    + " | rootMsg=" + root.getMessage();
        }
    }
}
