package org.example.restaurant_system.Services;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.restaurant_system.Repositories.FoodCategoryRepository;
import org.example.restaurant_system.Repositories.UserRepository;
import org.example.restaurant_system.models.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    public CategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public FoodCategory getCategory(Integer id) {
        return foodCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Category not found with id: " + id));
    }

    public String addCategory(@NotBlank(message = "categoryName is required") String categoryName) {
        categoryName = categoryName.trim();

        if (foodCategoryRepository.existsByCategoryName(categoryName)) {
            throw new IllegalStateException("Category name already exists");
        }

        FoodCategory category = FoodCategory.builder()
                .categoryName(categoryName)
                .build();

        foodCategoryRepository.save(category);
        return "category " + categoryName + " is added successfully" ;
    }

    public void DeleteCategory(Integer id) {
        if(!foodCategoryRepository.existsById(id)){
            throw new IllegalStateException("the category not found");
        }
        foodCategoryRepository.deleteById(id);
    }
}
