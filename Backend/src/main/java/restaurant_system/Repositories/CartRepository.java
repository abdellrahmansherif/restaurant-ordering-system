package restaurant_system.Repositories;

import restaurant_system.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUser_UserId(Integer userId);

    boolean existsByUser_UserId(Integer userId);
}