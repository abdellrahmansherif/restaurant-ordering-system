package org.example.restaurant_system.Controller;


import org.example.restaurant_system.Repositories.CartRepository;
import org.example.restaurant_system.Services.CartService;
import org.example.restaurant_system.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("")
    private ResponseEntity<?> GetCart(@AuthenticationPrincipal UserPrincipal userPrincipal){
        try {
            return ResponseEntity.ok(cartService.GetCart(userPrincipal));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @DeleteMapping("clear")
    private ResponseEntity<?> clearCart(@AuthenticationPrincipal UserPrincipal userPrincipal){
        try {
            return ResponseEntity.ok(cartService.clearCart(userPrincipal));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
