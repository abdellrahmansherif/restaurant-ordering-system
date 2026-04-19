package org.example.restaurant_system.Controller;


import org.example.restaurant_system.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;

//    @PostMapping("AddToCart")
//    private ResponseEntity<?> AddToCart()
//    {
//
//    }
}
