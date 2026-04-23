package restaurant_system.Controller;

import restaurant_system.DTO.AddFoodRequest;
import restaurant_system.Services.FoodService;
import restaurant_system.models.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getFood(@PathVariable("id") Integer id) {
        try {
            Food food = foodService.getFood(id);
            return ResponseEntity.ok(food);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PostMapping("/AddFood")
    public ResponseEntity<?> addFood(@RequestBody AddFoodRequest req) {
        try {
            return ResponseEntity.status(201).body(foodService.addFood(req));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable("id") Integer id) {
        try {
            foodService.deleteFood(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong while deleting food");
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getFoodsByCategory(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(foodService.getFoodsByCategory(id));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong while retrieving foods");
        }
    }
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableFoods() {
        try {
            return ResponseEntity.ok(foodService.getAvailableFoods());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchFoodsByName(@RequestParam("name") String name) {
        try {
            return ResponseEntity.ok(foodService.searchFoodsByName(name));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

}
