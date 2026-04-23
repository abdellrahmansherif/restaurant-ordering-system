export interface Order {
  orderId: number;
  items: OrderItem[];
  totalPrice: number;
}

export interface OrderItem {
  foodName: string;
  quantity: number;
  totalPrice: number;
}

export interface OrderResponse {
  orderId: number;
  items: OrderItem[];
  totalPrice: number;
}
