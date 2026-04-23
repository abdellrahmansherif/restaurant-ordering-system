import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/core';
import { FoodService } from '../../services/food.service';
import { CategoryService } from '../../services/category.service';
import { CartService } from '../../services/cart.service';
import { Food, FoodCategory } from '../../models/food.model';

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
  categories: FoodCategory[] = [];
  searchQuery = '';
  selectedCategory: string | null = null;
  isLoading = false;
  errorMessage = '';

  constructor(
    private foodService: FoodService,
    private categoryService: CategoryService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.loadFoods();
    this.loadCategories();
  }

  loadFoods(): void {
    this.isLoading = true;
    this.foodService.getAvailableFoods().subscribe({
      next: (foods) => {
        this.foods = foods;
        this.applyFilters();
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Failed to load menu items';
        this.isLoading = false;
      }
    });
  }

  loadCategories(): void {
    this.categoryService.getAllCategories().subscribe({
      next: (categories) => {
        this.categories = categories;
      },
      error: (error) => {
        console.error('Failed to load categories');
      }
    });
  }

  searchFoods(): void {
    this.applyFilters();
  }

  filterByCategory(categoryName: string | null): void {
    this.selectedCategory = categoryName;
    this.applyFilters();
  }

  private applyFilters(): void {
    let result = [...this.foods];

    // Apply search filter
    if (this.searchQuery.trim()) {
      const query = this.searchQuery.toLowerCase();
      result = result.filter(food =>
        food.foodName.toLowerCase().includes(query)
      );
    }

    // Apply category filter
    if (this.selectedCategory) {
      result = result.filter(food => food.categoryName === this.selectedCategory);
    }

    this.filteredFoods = result;
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

  get uniqueCategories(): string[] {
    const categoryNames = this.foods
      .map(f => f.categoryName)
      .filter(name => name !== undefined && name !== null) as string[];
    return [...new Set(categoryNames)];
  }
}
