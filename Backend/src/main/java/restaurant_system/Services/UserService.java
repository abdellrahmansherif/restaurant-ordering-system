package restaurant_system.Services;

import restaurant_system.DTO.LoginRequest;
import restaurant_system.DTO.LoginResponse;
import restaurant_system.DTO.RegisterRequest;
import restaurant_system.Repositories.UserRepository;
import restaurant_system.Security.JWTservice;
import restaurant_system.models.Role;
import restaurant_system.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTservice jwTservice;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse register(RegisterRequest Requset) {
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
        user.setPassword(passwordEncoder.encode(Requset.password())); // Encrypt password
        user.setPhoneNumber(
                Requset.phoneNumber() == null || Requset.phoneNumber().isBlank()
                        ? null
                        : Requset.phoneNumber()
        );

        user.setRole(Role.USER);
        userRepository.save(user);

        // Generate token for auto-login after registration
        String token = jwTservice.generateToken(user.getEmail());
        return new LoginResponse(token);
    }

    public LoginResponse signin(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        String token = jwTservice.generateToken(request.email());
        return new LoginResponse(token);
    }
}
