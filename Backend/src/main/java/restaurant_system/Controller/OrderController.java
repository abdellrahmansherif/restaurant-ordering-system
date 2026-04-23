package restaurant_system.Controller;

import restaurant_system.Services.OrderService;
import restaurant_system.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    public OrderService orderService;
    @PostMapping("/checkout")
    public ResponseEntity<?> makeOrder(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        try {
            return ResponseEntity.ok(orderService.makeOrder(userPrincipal));
        }
        catch (IllegalStateException e){
            return ResponseEntity.status(409).body(e.getMessage()); // conflict
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error");
        }
    }
}
