package org.example.restaurant_system.DTO;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        int orderID,
        List<orderComponents>orderComponents,
        BigDecimal TotalPrice
) {
}
