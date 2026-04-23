import { Food } from './food.model';

export interface CartItem {
  cartItemId?: number;
  food: Food;
  quantity: number;
  totalPrice: number;
}

export interface Cart {
  cartId?: number;
  totalPrice: number;
  cartItems: CartItem[];
}
