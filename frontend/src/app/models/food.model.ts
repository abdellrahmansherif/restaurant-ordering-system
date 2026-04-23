export interface Food {
  foodId?: number;
  foodName: string;
  price: number;
  isAvailable: boolean;
  categoryName?: string;
  category?: FoodCategory;
}

export interface FoodCategory {
  categoryId?: number;
  categoryName: string;
}

export interface AddFoodRequest {
  foodName: string;
  price: number;
  isAvailable: boolean;
  categoryId: number;
}
