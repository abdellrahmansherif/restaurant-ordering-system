package restaurant_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "food",
        indexes = {
                @Index(name = "idx_food_name", columnList = "food_name"),
                @Index(name = "idx_food_category_id", columnList = "category_id"),
                @Index(name = "idx_food_is_available", columnList = "is_available")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "food_name", nullable = false, length = 150)
    private String foodName;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_food_category")
    )
    private FoodCategory category;

    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();
}