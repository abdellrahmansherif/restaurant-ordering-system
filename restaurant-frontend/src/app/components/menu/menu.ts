import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FoodService } from '../../services/food.service';
import { CartService } from '../../services/cart.service';
import { Food } from '../../models/food.model';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './menu.html',
  styleUrl: './menu.scss',
})
export class Menu implements OnInit {
  foods: Food[] = [];
  filteredFoods: Food[] = [];
  searchQuery = '';
  isLoading = false;
  errorMessage = '';

  constructor(
    private foodService: FoodService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.loadFoods();
  }

  loadFoods(): void {
    this.isLoading = true;
    this.foodService.getAvailableFoods().subscribe({
      next: (foods) => {
        this.foods = foods;
        this.filteredFoods = foods;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Failed to load menu items';
        this.isLoading = false;
      }
    });
  }

  searchFoods(): void {
    if (!this.searchQuery.trim()) {
      this.filteredFoods = this.foods;
      return;
    }

    this.foodService.searchFoodsByName(this.searchQuery).subscribe({
      next: (foods) => {
        this.filteredFoods = foods;
      },
      error: (error) => {
        this.errorMessage = 'Search failed';
      }
    });
  }

  addToCart(food: Food): void {
    this.cartService.addToCart(food, 1);
    this.showNotification('Added to cart!');
  }

  private showNotification(message: string): void {
    const notification = document.createElement('div');
    notification.textContent = message;
    notification.style.cssText = `
      position: fixed;
      top: 100px;
      right: 20px;
      background: #4caf50;
      color: white;
      padding: 1rem 2rem;
      border-radius: 10px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.2);
      z-index: 10000;
      animation: slideIn 0.3s ease-out;
    `;
    document.body.appendChild(notification);
    setTimeout(() => notification.remove(), 2000);
  }
}
