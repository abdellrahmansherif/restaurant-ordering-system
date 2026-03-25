package org.example.restaurant_system.Services;

import jakarta.validation.constraints.NotBlank;
import org.example.restaurant_system.DTO.AddFoodRequest;
import org.example.restaurant_system.Repositories.FoodCategoryRepository;
import org.example.restaurant_system.Repositories.FoodRepository;
import org.example.restaurant_system.models.Food;
import org.example.restaurant_system.models.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String addFood(AddFoodRequest req) {
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

        return "food " + newFood.getFoodName() + " is added successfully";
    }

    public void DeleteFood(Integer id) {
        if(!foodRepository.existsById(id)){
            throw new IllegalStateException("the Food not found");
        }
        foodRepository.deleteById(id);
    }

    public List<Food> GetFoodsByCategory(Integer id)
    {
        if(foodCategoryRepository.existsById(id)) {
            return foodRepository.findByCategory_CategoryId(id);
        }
        else{
            throw new IllegalStateException("category noy found");
        }
    }
    public List<Food> getAvailableFoods() {
        return foodRepository.findByIsAvailableTrue();
    }
    public List<Food> searchFoodsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Food name is required");
        }

        return foodRepository.findByFoodNameContainingIgnoreCase(name.trim());
    }
}
