package restaurant_system.Repositories;

import restaurant_system.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder_OrderId(Integer orderId);

    Optional<OrderItem> findByOrder_OrderIdAndFood_FoodId(Integer orderId, Integer foodId);

    boolean existsByOrder_OrderIdAndFood_FoodId(Integer orderId, Integer foodId);
}