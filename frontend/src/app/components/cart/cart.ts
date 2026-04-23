import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { OrderService } from '../../services/order.service';
import { AuthService } from '../../services/auth.service';
import { Cart as CartModel } from '../../models/cart.model';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './cart.html',
  styleUrl: './cart.scss',
})
export class Cart implements OnInit {
  cart: CartModel = { totalPrice: 0, cartItems: [] };
  isCheckingOut = false;
  checkoutMessage = '';
  checkoutError = '';

  constructor(
    private cartService: CartService,
    private orderService: OrderService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cartService.cart$.subscribe(cart => {
      this.cart = cart;
    });
  }

  updateQuantity(foodId: number, change: number): void {
    const item = this.cart.cartItems.find(i => i.food.foodId === foodId);
    if (item) {
      const newQuantity = item.quantity + change;
      this.cartService.updateQuantity(foodId, newQuantity);
    }
  }

  removeItem(foodId: number): void {
    this.cartService.removeFromCart(foodId);
  }

  clearCart(): void {
    if (confirm('Are you sure you want to clear your cart?')) {
      this.cartService.clearCart();
    }
  }

  checkout(): void {
    if (!this.authService.isLoggedIn()) {
      alert('Please login to checkout');
      this.router.navigate(['/login']);
      return;
    }

    if (this.cart.cartItems.length === 0) {
      this.checkoutError = 'Your cart is empty';
      return;
    }

    this.isCheckingOut = true;
    this.checkoutError = '';
    this.checkoutMessage = '';

    this.orderService.checkout().subscribe({
      next: (order) => {
        this.isCheckingOut = false;
        this.checkoutMessage = `Order #${order.orderId} placed successfully! Total: $${order.totalPrice.toFixed(2)}`;
        this.cartService.clearCart();
        setTimeout(() => {
          this.checkoutMessage = '';
        }, 5000);
      },
      error: (error) => {
        this.isCheckingOut = false;
        this.checkoutError = error.error || 'Checkout failed. Please try again.';
      }
    });
  }

  getTax(): number {
    return this.cart.totalPrice * 0.1;
  }

  getGrandTotal(): number {
    return this.cart.totalPrice + this.getTax();
  }
}
