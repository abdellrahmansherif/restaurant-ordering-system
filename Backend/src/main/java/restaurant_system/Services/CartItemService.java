package restaurant_system.Services;

import restaurant_system.DTO.*;
import restaurant_system.DTO.AddCartItemRequest;
import restaurant_system.DTO.AddItemResponse;
import restaurant_system.DTO.CartItemDto;
import restaurant_system.DTO.UpdateCartItemRequest;
import restaurant_system.Repositories.CartItemRepository;
import restaurant_system.Repositories.CartRepository;
import restaurant_system.Repositories.FoodRepository;
import restaurant_system.Repositories.UserRepository;
import restaurant_system.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import restaurant_system.models.*;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@Validated
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public AddItemResponse addCartItem(AddCartItemRequest request, UserPrincipal userPrincipal) {
        String email = userPrincipal.getUsername();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        if (request.quantity() == null || request.quantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }


        Food food = foodRepository.findById(request.foodId())
                .orElseThrow(() -> new IllegalStateException("Food not found"));

        BigDecimal totalPriceItem = food.getPrice()
                .multiply(BigDecimal.valueOf(request.quantity()));

        Cart cart = cartRepository.findByUser_UserId(user.getUserId()).orElseGet(() -> {
            Cart newCart = Cart.builder()
                    .user(user)
                    .totalPrice(BigDecimal.ZERO)
                    .build();
            return cartRepository.save(newCart);
        });




        Optional<CartItem> existingCartItemOptional=
                cartItemRepository.findByCart_CartIdAndFood_FoodId(cart.getCartId(), food.getFoodId());

        CartItem cartItem;
        if(existingCartItemOptional.isPresent())
        {
            cartItem=existingCartItemOptional.get();
            int newQuantity=cartItem.getQuantity()+ request.quantity();
            cartItem.setQuantity(newQuantity);
            cartItem.setTotalPrice(
                    food.getPrice().multiply(BigDecimal.valueOf(newQuantity))
            );
        }
        else {
            cartItem = CartItem.builder()
                    .quantity(request.quantity())
                    .totalPrice(totalPriceItem)
                    .cart(cart)
                    .food(food)
                    .build();
        }
        cartItemRepository.save(cartItem);

        cart.setTotalPrice(cart.getTotalPrice().add(totalPriceItem));

        cartRepository.save(cart);



        CartItemDto cartItemDto = new CartItemDto(
                cartItem.getCartItemId(),
                food.getFoodId(),
                food.getFoodName(),
                cartItem.getQuantity(), // Use actual cart item quantity, not request quantity
                food.getPrice(),
                cartItem.getTotalPrice() // Use actual cart item total
        );

        AddItemResponse addItemResponse = new AddItemResponse(
                "Added to cart",
                cart.getCartId(),
                cart.getTotalPrice(),
                cartItemDto
        );
        return addItemResponse;
    }

    @Transactional
    public AddItemResponse updateItemCart(UpdateCartItemRequest request, UserPrincipal userPrincipal) {
        String email = userPrincipal.getUsername();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }

        if (request.quantity() == null || request.quantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }


        Food food = foodRepository.findById(request.foodId())
                .orElseThrow(() -> new IllegalStateException("Food not found"));



        Cart cart = cartRepository.findByUser_UserId(user.getUserId()).orElseGet(() -> {
            Cart newCart = Cart.builder()
                    .user(user)
                    .totalPrice(BigDecimal.ZERO)
                    .build();
            return cartRepository.save(newCart);
        });

        CartItem cartItem = cartItemRepository
                .findByCart_CartIdAndFood_FoodId(cart.getCartId(), food.getFoodId())
                .orElseThrow(() -> new IllegalStateException("Cart item not found"));



        BigDecimal oldItemTotal=cartItem.getTotalPrice();
        BigDecimal newItemTotal=food.getPrice().multiply(BigDecimal.valueOf(request.quantity()));

        cartItem.setQuantity(request.quantity());
        if (request.quantity() == 0) {
            cartItemRepository.delete(cartItem);

            BigDecimal newCartTotal = cart.getTotalPrice().subtract(oldItemTotal);
            cart.setTotalPrice(newCartTotal);
            cartRepository.save(cart);

            return new AddItemResponse(
                    "Cart item removed successfully",
                    cart.getCartId(),
                    cart.getTotalPrice(),
                    null
            );
        }

        cartItem.setTotalPrice(newItemTotal);


        cartItemRepository.save(cartItem);

        BigDecimal newCartTotal = cart.getTotalPrice()
                .subtract(oldItemTotal)
                .add(newItemTotal);

        cart.setTotalPrice(newCartTotal);
        cartRepository.save(cart);

        CartItemDto cartItemDto = new CartItemDto(
                cartItem.getCartItemId(),
                food.getFoodId(),
                food.getFoodName(),
                cartItem.getQuantity(),
                food.getPrice(),
                cartItem.getTotalPrice()
        );

        return new AddItemResponse(
                "Cart item updated successfully",
                cart.getCartId(),
                cart.getTotalPrice(),
                cartItemDto
        );


    }
}
