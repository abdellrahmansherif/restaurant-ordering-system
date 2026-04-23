package restaurant_system.Repositories;

import restaurant_system.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser_UserId(Integer userId);

    List<Order> findByPaymentMethod(String paymentMethod);

    List<Order> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);

    List<Order> findByUser_UserIdOrderByOrderDateDesc(Integer userId);
}