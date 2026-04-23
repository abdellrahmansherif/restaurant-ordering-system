package restaurant_system.Services;


import restaurant_system.DTO.OrderResponse;
import restaurant_system.DTO.orderComponents;
import restaurant_system.Repositories.CartRepository;
import restaurant_system.Repositories.OrderItemRepository;
import restaurant_system.Repositories.OrderRepository;
import restaurant_system.Repositories.UserRepository;
import restaurant_system.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restaurant_system.models.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Transactional
    public OrderResponse makeOrder(UserPrincipal userPrincipal) {
        String email = userPrincipal.getUsername();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        Cart cart = cartRepository.findByUser_UserId(user.getUserId()).orElseGet(() -> {
            Cart cart1 = Cart.builder()
                    .totalPrice(BigDecimal.ZERO)
                    .user(user)
                    .build();
            return cartRepository.save(cart1);
        });
        if (cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        Order order=new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setPaymentMethod("cash");

        orderRepository.save(order);

        List<orderComponents>orders=new ArrayList<>();

        for(CartItem it:cart.getCartItems()) {
            OrderItem orderItem = OrderItem.builder()
                    .food(it.getFood())
                    .totalPrice(it.getTotalPrice())
                    .quantity(it.getQuantity())
                    .order(order)
                    .build();
            orderComponents orderComponents1=new orderComponents(
                    it.getFood().getFoodName(),
                    it.getQuantity(),
                    it.getTotalPrice()
            );
            orders.add(orderComponents1);
            orderItemRepository.save(orderItem);
        }
        BigDecimal TotalPrice=cart.getTotalPrice();
        cartService.clearCart(userPrincipal);

        return new OrderResponse(
                order.getOrderId(), orders, TotalPrice
        ) ;
    }
}
