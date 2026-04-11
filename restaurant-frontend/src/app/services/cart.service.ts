import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Cart, CartItem } from '../models/cart.model';
import { Food } from '../models/food.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartSubject: BehaviorSubject<Cart>;
  public cart$: Observable<Cart>;

  constructor() {
    const savedCart = localStorage.getItem('cart');
    const initialCart: Cart = savedCart ? JSON.parse(savedCart) : { totalPrice: 0, cartItems: [] };
    this.cartSubject = new BehaviorSubject<Cart>(initialCart);
    this.cart$ = this.cartSubject.asObservable();
  }

  get cartValue(): Cart {
    return this.cartSubject.value;
  }

  addToCart(food: Food, quantity: number = 1): void {
    const cart = this.cartValue;
    const existingItem = cart.cartItems.find(item => item.food.foodId === food.foodId);

    if (existingItem) {
      existingItem.quantity += quantity;
      existingItem.totalPrice = existingItem.quantity * existingItem.food.price;
    } else {
      const newItem: CartItem = {
        food: food,
        quantity: quantity,
        totalPrice: food.price * quantity
      };
      cart.cartItems.push(newItem);
    }

    this.updateCart(cart);
  }

  removeFromCart(foodId: number): void {
    const cart = this.cartValue;
    cart.cartItems = cart.cartItems.filter(item => item.food.foodId !== foodId);
    this.updateCart(cart);
  }

  updateQuantity(foodId: number, quantity: number): void {
    const cart = this.cartValue;
    const item = cart.cartItems.find(item => item.food.foodId === foodId);

    if (item) {
      if (quantity <= 0) {
        this.removeFromCart(foodId);
      } else {
        item.quantity = quantity;
        item.totalPrice = item.quantity * item.food.price;
        this.updateCart(cart);
      }
    }
  }

  clearCart(): void {
    const cart: Cart = { totalPrice: 0, cartItems: [] };
    this.updateCart(cart);
  }

  private updateCart(cart: Cart): void {
    cart.totalPrice = cart.cartItems.reduce((total, item) => total + item.totalPrice, 0);
    localStorage.setItem('cart', JSON.stringify(cart));
    this.cartSubject.next(cart);
  }

  getItemCount(): number {
    return this.cartValue.cartItems.reduce((count, item) => count + item.quantity, 0);
  }
}
