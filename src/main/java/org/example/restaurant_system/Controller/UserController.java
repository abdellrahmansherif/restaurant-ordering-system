package org.example.restaurant_system.Controller;

import jakarta.validation.Valid;
import org.example.restaurant_system.DTO.LoginRequest;
import org.example.restaurant_system.DTO.RegisterRequest;
import org.example.restaurant_system.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("register")
    public ResponseEntity<?> Register(@Valid @RequestBody RegisterRequest Request)
    {
            try{
                return ResponseEntity.ok(userService.Register(Request));
            }catch (IllegalStateException e){
                return ResponseEntity.status(409).body(e.getMessage()); // conflict
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Unexpected error");
            }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest Request)
    {
        try{
            return ResponseEntity.ok(userService.Signin(Request));
        }catch (IllegalStateException e){
            return ResponseEntity.status(409).body(e.getMessage()); // conflict
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error");
        }
    }
}
