package org.example.restaurant_system.Repositories;

import org.example.restaurant_system.models.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {

    Optional<FoodCategory> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);
}