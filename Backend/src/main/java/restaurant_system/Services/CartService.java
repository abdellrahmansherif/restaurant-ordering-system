package restaurant_system.Services;

import restaurant_system.DTO.CartItemDto;
import restaurant_system.DTO.CartResponse;
import restaurant_system.Repositories.CartItemRepository;
import restaurant_system.Repositories.CartRepository;
import restaurant_system.Repositories.UserRepository;
import restaurant_system.models.Cart;
import restaurant_system.models.User;
import restaurant_system.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartResponse getCart(UserPrincipal userPrincipal) {
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

        return mapToCartResponse(cart);
    }

    private CartResponse mapToCartResponse(Cart cart) {
        List<CartItemDto> cartItemDtos = cart.getCartItems()
                .stream()
                .map(cartItem -> new CartItemDto(
                        cartItem.getCartItemId(),
                        cartItem.getFood().getFoodId(),
                        cartItem.getFood().getFoodName(),
                        cartItem.getQuantity(),
                        cartItem.getFood().getPrice(),
                        cartItem.getTotalPrice()
                ))
                .toList();

        return new CartResponse(
                cart.getCartId(),
                cart.getTotalPrice(),
                cartItemDtos
        );
    }

    public CartResponse clearCart(UserPrincipal userPrincipal) {
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
        cartItemRepository.deleteByCart_CartId(cart.getCartId());
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
        return new CartResponse(
                cart.getCartId(),
                cart.getTotalPrice(),
                new ArrayList<>()
        );
    }
}
