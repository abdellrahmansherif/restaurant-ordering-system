package org.example.restaurant_system.DTO;

public record RegisterResponse(
        Integer id,
        String name,
        String email,
        String role

) {}
