package restaurant_system.DTO;

import java.math.BigDecimal;

public record orderComponents(
        String foodName,
        int quantity,
        BigDecimal Price
) {

}
