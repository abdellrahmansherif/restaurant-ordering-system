package restaurant_system.Services;

import restaurant_system.Controller.AddFoodResponse;
import restaurant_system.Controller.FoodDto;
import restaurant_system.DTO.AddFoodRequest;
import restaurant_system.Repositories.FoodCategoryRepository;
import restaurant_system.Repositories.FoodRepository;
import restaurant_system.models.Food;
import restaurant_system.models.FoodCategory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodCategoryRepository foodCategoryRepository;


    public FoodService(FoodRepository foodRepository, FoodCategoryRepository foodCategoryRepository) {
        this.foodRepository = foodRepository;
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public Food getFood(Integer id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("food not found with id: " + id));
    }

    public AddFoodResponse addFood(AddFoodRequest req) {
        if (req == null) {
            throw new IllegalStateException("Food body is required");
        }

        String foodName = req.foodName();

        if (foodName == null || foodName.trim().isEmpty()) {
            throw new IllegalStateException("Food name is required");
        }

        foodName = foodName.trim();

        if (foodRepository.existsByFoodName(foodName)) {
            throw new IllegalStateException("Food name already exists");
        }

        if (req.price() == null || req.price().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Price must be greater than 0");
        }

        if (req.categoryId() == null) {
            throw new IllegalStateException("Category is required");
        }

        FoodCategory category = foodCategoryRepository.findById(req.categoryId())
                .orElseThrow(() -> new IllegalStateException("Category not found"));

        Food newFood = Food.builder()
                .foodName(foodName)
                .price(req.price())
                .isAvailable(req.isAvailable() != null ? req.isAvailable() : true)
                .category(category)
                .build();

        foodRepository.save(newFood);

        return new AddFoodResponse(
                "Food added successfully",
                newFood.getFoodId(),
                newFood.getFoodName(),
                newFood.getPrice(),
                newFood.getIsAvailable(),
                category.getCategoryId(),
                category.getCategoryName()
        );
    }

    public void deleteFood(Integer id) {
        if (!foodRepository.existsById(id)) {
            throw new IllegalStateException("Food not found");
        }
        foodRepository.deleteById(id);
    }

    public List<Food> getFoodsByCategory(Integer id) {
        if (foodCategoryRepository.existsById(id)) {
            return foodRepository.findByCategory_CategoryId(id);
        } else {
            throw new IllegalStateException("Category not found");
        }
    }
    public List<Food> searchFoodsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Food name is required");
        }

        return foodRepository.findByFoodNameContainingIgnoreCase(name.trim());
    }
    public List<FoodDto> getAvailableFoods() {
        return foodRepository.findByIsAvailableTrue()
                .stream()
                .map(food -> new FoodDto(
                        food.getFoodId(),
                        food.getFoodName(),
                        food.getPrice(),
                        food.getIsAvailable(),
                        food.getCategory().getCategoryName()
                ))
                .toList();
    }
}
