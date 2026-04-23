package restaurant_system.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Entity
@Table(
        name = "cart_item",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_cart_item_cart_food", columnNames = {"cart_id", "food_id"})
        },
        indexes = {
                @Index(name = "idx_cart_item_cart_id", columnList = "cart_id"),
                @Index(name = "idx_cart_item_food_id", columnList = "food_id")
        }
)
@Check(constraints = "quantity > 0")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Integer cartItemId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cart_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_cart_item_cart")
    )
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "food_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_cart_item_food")
    )
    private Food food;
}