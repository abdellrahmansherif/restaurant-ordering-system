package restaurant_system.Repositories;

import jakarta.transaction.Transactional;
import restaurant_system.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCart_CartId(Integer cartId);

    Optional<CartItem> findByCart_CartIdAndFood_FoodId(Integer cartId, Integer foodId);

    boolean existsByCart_CartIdAndFood_FoodId(Integer cartId, Integer foodId);

    void deleteByCart_CartIdAndFood_FoodId(Integer cartId, Integer foodId);

    @Transactional
    void deleteByCart_CartId(Integer cartId);
}