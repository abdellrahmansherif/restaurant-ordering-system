package org.example.restaurant_system.Controller;

import jakarta.validation.Valid;
import org.example.restaurant_system.Services.CategoryService;
import org.example.restaurant_system.models.FoodCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Integer id) {
        try {
            FoodCategory category = categoryService.getCategory(id);
            return ResponseEntity.ok(category);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("AddCategory")
    public ResponseEntity<?> AddCategory(@Valid @RequestBody FoodCategory category){
        try{
            return ResponseEntity.ok(categoryService.addCategory(category.getCategoryName()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage()); // conflict
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>DeleteCategory(@PathVariable("id") Integer id)
    {
        try {
            categoryService.DeleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalStateException e)
        {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong while deleting category");
        }
    }


}
