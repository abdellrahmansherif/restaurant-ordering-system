package restaurant_system.Controller;

import java.math.BigDecimal;

public record FoodDto(
        Integer foodId,
        String foodName,
        BigDecimal price,
        Boolean isAvailable,
        String categoryName
) {}
