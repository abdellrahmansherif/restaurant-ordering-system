package org.example.restaurant_system.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCartItemRequest(
        @NotNull(message = "Food id is required")
        Integer foodId,

        @NotNull(message = "Quantity is required")
        @Min(value = 0, message = "Quantity must be at least 0")
        Integer quantity
) {
}
