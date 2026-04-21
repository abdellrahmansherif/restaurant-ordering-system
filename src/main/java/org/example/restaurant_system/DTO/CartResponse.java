package org.example.restaurant_system.DTO;

import org.example.restaurant_system.models.CartItem;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(
        int cartId,
        BigDecimal subtotal,
        List<CartItemDto> cartItemDto
) {
}
