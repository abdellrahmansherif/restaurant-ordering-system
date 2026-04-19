package org.example.restaurant_system.DTO;

import java.math.BigDecimal;

public record CartItemDto(
        Integer CartItemId,
        Integer foodId,
        String foodName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) {



}
