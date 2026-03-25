package org.example.restaurant_system.Repositories;

import org.example.restaurant_system.models.Food;
import org.example.restaurant_system.models.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    boolean existsByFoodName(String foodName);

    List<Food> findByIsAvailableTrue();

    List<Food> findByCategory(FoodCategory category);

    List<Food> findByCategory_CategoryId(Integer categoryId);

    List<Food> findByFoodNameContainingIgnoreCase(String foodName);

    List<Food> findByCategory_CategoryNameIgnoreCase(String categoryName);

    List<Food> findByCategory_CategoryNameIgnoreCaseAndIsAvailableTrue(String categoryName);
}