package org.example.restaurant_system.Controller;

import java.math.BigDecimal;

public record AddFoodResponse(
        String message,
        Integer foodId,
        String foodName,
        BigDecimal price,
        Boolean isAvailable,
        Integer categoryId,
        String categoryName
) {
}
