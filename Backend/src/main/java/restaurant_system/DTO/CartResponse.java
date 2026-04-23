package restaurant_system.DTO;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(
        int cartId,
        BigDecimal subtotal,
        List<CartItemDto> cartItemDto
) {
}
