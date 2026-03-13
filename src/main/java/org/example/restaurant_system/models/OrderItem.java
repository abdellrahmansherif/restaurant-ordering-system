package org.example.restaurant_system.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Entity
@Table(
        name = "order_item",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_order_item_order_food", columnNames = {"order_id", "food_id"})
        },
        indexes = {
                @Index(name = "idx_order_item_food_id", columnList = "food_id"),
                @Index(name = "idx_order_item_order_id", columnList = "order_id")
        }
)
@Check(constraints = "quantity > 0")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "food_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_item_food")
    )
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_order_item_order")
    )
    private Order order;
}