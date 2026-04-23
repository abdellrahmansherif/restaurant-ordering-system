package restaurant_system.DTO;

import java.math.BigDecimal;

public record AddFoodRequest(
        String foodName,
        BigDecimal price,
        Boolean isAvailable,
        Integer categoryId
) {
}