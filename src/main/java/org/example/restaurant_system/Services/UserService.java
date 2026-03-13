package org.example.restaurant_system.Services;

import org.example.restaurant_system.DTO.RegisterRequest;
import org.example.restaurant_system.DTO.RegisterResponse;
import org.example.restaurant_system.Repositories.UserRepository;
import org.example.restaurant_system.models.Role;
import org.example.restaurant_system.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

        RegisterResponse Response=new RegisterResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getRole().name()
                );

        return Response;
    }
}
