# Restaurant Ordering System - Setup Guide

## 🎉 Project Overview

A complete **Restaurant Ordering System** with:
- **Backend**: Spring Boot + MySQL + JWT Authentication
- **Frontend**: Angular 18 with Modern Material Design UI

---

## 📋 Prerequisites

### Required Software:
1. **Java 17+** - For Spring Boot backend
2. **Maven 3.6+** - Build tool (included via Maven Wrapper)
3. **MySQL 8.0+** - Database server
4. **Node.js 18+** - For Angular frontend
5. **Angular CLI 18** - Frontend framework

---

## 🗄️ Database Setup

### 1. Start MySQL Server
Make sure MySQL is running on `localhost:3306`

### 2. Create Database
```sql
CREATE DATABASE food_ordering_platform;
```

### 3. Configure Database Credentials
Edit: `restaurant-ordering-system/src/main/resources/application.properties`

```properties
spring.application.name=Restaurant_System
spring.datasource.url=jdbc:mysql://localhost:3306/food_ordering_platform
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Note**: Hibernate will automatically create all tables on first run.

---

## 🚀 Backend Setup (Spring Boot)

### 1. Navigate to Backend Directory
```bash
cd restaurant-ordering-system
```

### 2. Build the Project
```bash
./mvnw clean install
```

### 3. Run the Backend
```bash
./mvnw spring-boot:run
```

**Backend will start on**: `http://localhost:8080`

### 4. Verify Backend is Running
Open browser and check: `http://localhost:8080/foods/available`

You should see an empty array `[]` initially (no data yet).

---

## 🎨 Frontend Setup (Angular)

### 1. Navigate to Frontend Directory
```bash
cd restaurant-frontend
```

### 2. Install Dependencies (if needed)
```bash
npm install
```

### 3. Run the Frontend
```bash
ng serve --open
```

**Frontend will start on**: `http://localhost:4200`

Your browser should automatically open to the application!

---

## 📱 Application Features

### 1. **Navigation Bar**
- Logo and brand name
- Links to Menu, Cart
- Login/Register options
- Cart badge showing item count

### 2. **Registration Page** (`/register`)
- Create new user account
- Fields: Name, Email, Phone, Password
- Automatic login after registration
- Redirects to menu

### 3. **Login Page** (`/login`)
- Email and password authentication
- JWT token stored in localStorage
- Redirects to menu after login

### 4. **Menu Page** (`/menu`)
- Display all available food items
- Search functionality
- Add items to cart
- Beautiful card-based layout

### 5. **Cart Page** (`/cart`)
- View cart items
- Adjust quantities (+/- buttons)
- Remove items
- See total with tax calculation
- Clear entire cart
- Proceed to checkout

---

## 🎯 Testing the Application

### Step 1: Seed Database (Optional)

You can manually add categories and foods via API calls or create a simple SQL script:

```sql
-- Insert Categories
INSERT INTO food_category (category_name) VALUES ('Pizza');
INSERT INTO food_category (category_name) VALUES ('Pasta');
INSERT INTO food_category (category_name) VALUES ('Drinks');

-- Insert Foods
INSERT INTO food (food_name, price, is_available, category_id)
VALUES ('Margherita Pizza', 12.99, true, 1);

INSERT INTO food (food_name, price, is_available, category_id)
VALUES ('Pepperoni Pizza', 14.99, true, 1);

INSERT INTO food (food_name, price, is_available, category_id)
VALUES ('Carbonara Pasta', 11.99, true, 2);

INSERT INTO food (food_name, price, is_available, category_id)
VALUES ('Coca Cola', 2.99, true, 3);
```

### Step 2: Test User Flow

1. **Open Application**: `http://localhost:4200`
2. **Register**: Create a new account
3. **Browse Menu**: See all available items
4. **Search**: Try searching for items
5. **Add to Cart**: Click "Add to Cart" on items
6. **View Cart**: Check cart badge and navigate to cart
7. **Manage Cart**: Update quantities, remove items
8. **Checkout**: Click "Proceed to Checkout"

---

## 🔧 API Integration

### Frontend Configuration

All API calls are configured in services located at:
```
restaurant-frontend/src/app/services/
```

**Base URL**: `http://localhost:8080`

### Services Available:
1. **AuthService** - Login, Register, Logout
2. **FoodService** - Get foods, search, add/delete
3. **CategoryService** - Manage categories
4. **CartService** - Local cart management

### JWT Token Handling

Tokens are automatically:
- Stored in localStorage on login
- Retrieved for authenticated requests
- Removed on logout

---

## 📁 Project Structure

```
restaurant-ordering-system/
├── src/main/java/org/example/restaurant_system/
│   ├── Controller/          # REST Controllers
│   ├── DTO/                 # Data Transfer Objects
│   ├── models/              # JPA Entities
│   ├── Repositories/        # Data Access Layer
│   ├── Security/            # JWT & Security Config
│   └── Services/            # Business Logic
├── restaurant-frontend/
│   └── src/app/
│       ├── components/      # UI Components
│       ├── services/        # API Services
│       ├── models/          # TypeScript Interfaces
│       └── guards/          # Route Guards
└── pom.xml
```

---

## 🎨 UI Features

### Modern Design Elements:
- ✨ Gradient backgrounds
- 🎯 Smooth animations
- 📱 Responsive layout
- 🎨 Material Design inspired
- 🔔 Toast notifications
- 💾 Persistent cart (localStorage)

### Color Scheme:
- Primary: Purple-Blue Gradient (#667eea to #764ba2)
- Success: Green (#4caf50)
- Error: Red (#ff4444)
- Background: Light Gray (#f5f5f5)

---

## 🐛 Troubleshooting

### Backend Won't Start
- ✅ Check MySQL is running
- ✅ Verify database exists
- ✅ Check credentials in application.properties
- ✅ Ensure port 8080 is not in use

### Frontend Build Errors
- ✅ Run `npm install` to ensure dependencies
- ✅ Check Node.js version (18+)
- ✅ Clear cache: `rm -rf node_modules package-lock.json && npm install`

### CORS Errors
- ✅ Backend has CORS disabled in SecurityConfig
- ✅ All requests permitted via `anyRequest().permitAll()`

### Cart Not Persisting
- ✅ Check browser localStorage
- ✅ Ensure browser allows localStorage
- ✅ Check browser console for errors

---

## 🔒 Security Notes

### Current Configuration:
- JWT tokens for authentication
- **Password encoding**: Currently using NoOpPasswordEncoder (NOT for production!)
- **CSRF**: Disabled for REST API
- **CORS**: Configured to allow all origins

### For Production:
1. Use BCryptPasswordEncoder
2. Configure proper CORS origins
3. Enable HTTPS
4. Add rate limiting
5. Implement refresh tokens
6. Add input validation

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Angular Documentation](https://angular.io/docs)
- [JWT.io](https://jwt.io/) - Learn about JWT tokens

---

## 🎊 You're All Set!

Your restaurant ordering system is now ready to use. Enjoy building!

For questions or issues, refer to:
- API Documentation: `API_DOCUMENTATION.md`
- Backend code in `src/main/java/`
- Frontend code in `restaurant-frontend/src/app/`
