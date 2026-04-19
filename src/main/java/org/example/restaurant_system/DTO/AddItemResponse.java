package org.example.restaurant_system.DTO;

import java.math.BigDecimal;

public record AddItemResponse(
        String message,
        Integer cartId,
        BigDecimal subtotal,
        CartItemDto cartItem
) {
}