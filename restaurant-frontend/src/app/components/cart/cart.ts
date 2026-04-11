import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { Cart as CartModel, CartItem } from '../../models/cart.model';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './cart.html',
  styleUrl: './cart.scss',
})
export class Cart implements OnInit {
  cart: CartModel = { totalPrice: 0, cartItems: [] };

  constructor(private cartService: CartService) {}

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
    alert('Checkout functionality would be implemented here with the Order API!');
  }
}
