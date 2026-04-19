package org.example.restaurant_system.Controller;

import jakarta.validation.Valid;
import org.example.restaurant_system.DTO.AddCartItemRequest;
import org.example.restaurant_system.DTO.AddCartItemRequest;
import org.example.restaurant_system.DTO.UpdateCartItemRequest;
import org.example.restaurant_system.Repositories.CartItemRepository;
import org.example.restaurant_system.Services.CartItemService;
import org.example.restaurant_system.models.CartItem;
import org.example.restaurant_system.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartitem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/AddCartItem")
    public ResponseEntity<?> addCartItem(
            @Valid @RequestBody AddCartItemRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        try{
            return ResponseEntity.ok(cartItemService.addCartItem(request,userPrincipal));
         }
         catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @PutMapping("/updateCartItem")
    public ResponseEntity<?> updateItemCart(
            @Valid @RequestBody UpdateCartItemRequest request,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    )
    {
        try{
            return ResponseEntity.ok(cartItemService.updateItemCart(request,userPrincipal));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
