package org.example.restaurant_system.Repositories;

import org.example.restaurant_system.models.Role;
import org.example.restaurant_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);


    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findByRole(Role role);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}