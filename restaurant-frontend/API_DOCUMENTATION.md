# Restaurant Ordering System - API Documentation

## Base URL
```
http://localhost:8080
```

## Authentication Endpoints

### Register New User
- **URL**: `/register`
- **Method**: `POST`
- **Auth Required**: No
- **Request Body**:
```json
{
  "userName": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "phoneNumber": "+1234567890"
}
```
- **Success Response** (200):
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "userId": 1,
    "userName": "John Doe",
    "email": "john@example.com",
    "phoneNumber": "+1234567890",
    "role": "USER"
  }
}
```
- **Error Response** (409): User already exists

---

### Login
- **URL**: `/login`
- **Method**: `POST`
- **Auth Required**: No
- **Request Body**:
```json
{
  "email": "john@example.com",
  "password": "password123"
}
```
- **Success Response** (200):
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "userId": 1,
    "userName": "John Doe",
    "email": "john@example.com",
    "role": "USER"
  }
}
```
- **Error Response** (409): Invalid credentials

---

## Food Endpoints

### Get Available Foods
- **URL**: `/foods/available`
- **Method**: `GET`
- **Auth Required**: No
- **Success Response** (200):
```json
[
  {
    "foodId": 1,
    "foodName": "Margherita Pizza",
    "price": 12.99,
    "isAvailable": true,
    "category": {
      "categoryId": 1,
      "categoryName": "Pizza"
    }
  }
]
```

---

### Get Food by ID
- **URL**: `/foods/{id}`
- **Method**: `GET`
- **Auth Required**: No
- **URL Parameters**: `id` (integer)
- **Success Response** (200):
```json
{
  "foodId": 1,
  "foodName": "Margherita Pizza",
  "price": 12.99,
  "isAvailable": true,
  "category": {
    "categoryId": 1,
    "categoryName": "Pizza"
  }
}
```
- **Error Response** (404): Food not found

---

### Search Foods by Name
- **URL**: `/foods/search?name={searchTerm}`
- **Method**: `GET`
- **Auth Required**: No
- **Query Parameters**: `name` (string)
- **Success Response** (200):
```json
[
  {
    "foodId": 1,
    "foodName": "Margherita Pizza",
    "price": 12.99,
    "isAvailable": true
  }
]
```

---

### Get Foods by Category
- **URL**: `/foods/category/{categoryId}`
- **Method**: `GET`
- **Auth Required**: No
- **URL Parameters**: `categoryId` (integer)
- **Success Response** (200):
```json
[
  {
    "foodId": 1,
    "foodName": "Margherita Pizza",
    "price": 12.99,
    "isAvailable": true
  }
]
```
- **Error Response** (404): Category not found

---

### Add New Food (Admin)
- **URL**: `/foods/AddFood`
- **Method**: `POST`
- **Auth Required**: Yes (JWT Token)
- **Request Body**:
```json
{
  "foodName": "Pepperoni Pizza",
  "price": 14.99,
  "isAvailable": true,
  "categoryId": 1
}
```
- **Success Response** (201):
```json
{
  "foodId": 2,
  "foodName": "Pepperoni Pizza",
  "price": 14.99,
  "isAvailable": true,
  "category": {
    "categoryId": 1,
    "categoryName": "Pizza"
  }
}
```

---

### Delete Food (Admin)
- **URL**: `/foods/{id}`
- **Method**: `DELETE`
- **Auth Required**: Yes (JWT Token)
- **URL Parameters**: `id` (integer)
- **Success Response** (204): No Content
- **Error Response** (404): Food not found

---

## Category Endpoints

### Get Category by ID
- **URL**: `/categories/{id}`
- **Method**: `GET`
- **Auth Required**: No
- **URL Parameters**: `id` (integer)
- **Success Response** (200):
```json
{
  "categoryId": 1,
  "categoryName": "Pizza"
}
```
- **Error Response** (404): Category not found

---

### Add New Category (Admin)
- **URL**: `/categories/AddCategory`
- **Method**: `POST`
- **Auth Required**: Yes (JWT Token)
- **Request Body**:
```json
{
  "categoryName": "Desserts"
}
```
- **Success Response** (200):
```json
{
  "categoryId": 3,
  "categoryName": "Desserts"
}
```
- **Error Response** (409): Category already exists

---

### Delete Category (Admin)
- **URL**: `/categories/{id}`
- **Method**: `DELETE`
- **Auth Required**: Yes (JWT Token)
- **URL Parameters**: `id` (integer)
- **Success Response** (204): No Content
- **Error Response** (404): Category not found

---

## Authentication Headers

For endpoints requiring authentication, include the JWT token in the request headers:

```
Authorization: Bearer {token}
```

## Error Responses

All endpoints may return the following error responses:

- **400 Bad Request**: Invalid input data
- **401 Unauthorized**: Missing or invalid token
- **404 Not Found**: Resource not found
- **409 Conflict**: Resource already exists or conflict
- **500 Internal Server Error**: Server error

---

## Data Models

### User
```typescript
{
  userId: number;
  userName: string;
  email: string;
  phoneNumber?: string;
  role: "USER" | "ADMIN";
}
```

### Food
```typescript
{
  foodId: number;
  foodName: string;
  price: number;
  isAvailable: boolean;
  category: FoodCategory;
}
```

### FoodCategory
```typescript
{
  categoryId: number;
  categoryName: string;
}
```

### Cart (Frontend Only - Local Storage)
```typescript
{
  cartId?: number;
  totalPrice: number;
  cartItems: CartItem[];
}
```

### CartItem (Frontend Only)
```typescript
{
  cartItemId?: number;
  food: Food;
  quantity: number;
  totalPrice: number;
}
```
