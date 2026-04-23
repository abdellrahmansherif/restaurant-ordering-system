# 🍽️ Restaurant Ordering System

A full-stack restaurant ordering application built with **Spring Boot** (backend) and **Angular 18** (frontend).

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-18-red)](https://angular.dev)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)
[![Status](https://img.shields.io/badge/Status-Production%20Ready-success)](#)

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Quick Start](#quick-start)
- [Documentation](#documentation)
- [Screenshots](#screenshots)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Security](#security)
- [Contributing](#contributing)

---

## ✨ Features

### User Features
- 🔐 **User Authentication** - Secure registration and login with JWT
- 🍕 **Menu Browsing** - View all available food items
- 🔍 **Search & Filter** - Search by name and filter by category
- 🛒 **Shopping Cart** - Add, update, remove items with live totals
- 💳 **Checkout** - Complete order placement
- 📱 **Responsive Design** - Works on all devices

### Admin Features
- ➕ Add/Delete food items
- 📁 Manage categories
- 🔒 Role-based access control

### Technical Features
- 🔒 **BCrypt Password Encryption**
- 🎫 **JWT Token Authentication**
- 🔄 **HTTP Interceptor** for auto-token injection
- 💾 **JPA/Hibernate** for database management
- 🎨 **Modern UI** with Angular Material-inspired design
- ⚡ **Real-time Cart Updates**
- ✅ **Form Validation**
- 🚨 **Error Handling & Notifications**

---

## 🛠️ Tech Stack

### Backend
- **Framework**: Spring Boot 4.0.3
- **Language**: Java 17
- **Database**: MySQL 8.0
- **ORM**: JPA/Hibernate
- **Security**: Spring Security + JWT
- **Build Tool**: Maven

### Frontend
- **Framework**: Angular 18
- **Language**: TypeScript
- **Styling**: SCSS
- **State Management**: RxJS
- **HTTP Client**: Angular HttpClient
- **Components**: Standalone Components

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Node.js 18+
- MySQL 8.0+
- Maven 3.6+
- Angular CLI 18+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/abdellrahmansherif/restaurant-ordering-system.git
cd restaurant-ordering-system
```

2. **Setup Database**
```bash
mysql -u root -p
CREATE DATABASE food_ordering_platform;
EXIT;
```

3. **Configure Environment**
```bash
cp .env.example .env
# Edit .env and set your MySQL password and JWT secret
```

4. **Start Backend**
```bash
./mvnw spring-boot:run
```

5. **Start Frontend** (new terminal)
```bash
cd frontend
npm install
ng serve
```

6. **Access Application**
```
http://localhost:4200
```

### Default Ports
- Backend: `http://localhost:8080`
- Frontend: `http://localhost:4200`
- MySQL: `localhost:3306`

---

## 📚 Documentation

| Document | Description |
|----------|-------------|
| [COMPLETE_SETUP_GUIDE.md](COMPLETE_SETUP_GUIDE.md) | Full setup instructions with troubleshooting |
| [API_DOCUMENTATION.md](API_DOCUMENTATION.md) | Complete API reference with examples |
| [BACKEND_FIXES.md](BACKEND_FIXES.md) | All backend improvements and fixes |
| [FRONTEND_FIXES.md](frontend/FRONTEND_FIXES.md) | All frontend improvements and fixes |
| [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) | Complete project overview and status |

---

## 📸 Screenshots

### Home Page (Menu)
- Modern gradient navbar
- Search functionality
- Category filtering
- Food cards with prices and availability

### Shopping Cart
- Item quantity management
- Live price calculations
- Tax calculation (10%)
- Checkout integration

### Authentication
- Clean login/register forms
- Form validation
- Auto-redirect after login

---

## 📁 Project Structure

```
restaurant-ordering-system/
├── src/main/java/              # Backend source code
│   └── org/example/restaurant_system/
│       ├── Controller/         # REST Controllers
│       ├── Services/           # Business Logic
│       ├── Repositories/       # Data Access
│       ├── models/             # JPA Entities
│       ├── DTO/                # Data Transfer Objects
│       └── Security/           # Authentication & Security
├── src/main/resources/
│   └── application.properties  # Backend configuration
├── pom.xml                     # Maven dependencies
└── frontend/                   # Angular application
    ├── src/app/
    │   ├── components/         # UI Components
    │   ├── services/           # API Services
    │   ├── models/             # TypeScript Interfaces
    │   └── interceptors/       # HTTP Interceptors
    └── package.json            # npm dependencies
```

---

## 🌐 API Endpoints

### Authentication
- `POST /register` - Register new user
- `POST /login` - User login (returns JWT token)

### Foods
- `GET /foods/available` - Get all available foods
- `GET /foods/search?name={query}` - Search foods
- `GET /foods/category/{id}` - Get foods by category
- `POST /foods/AddFood` - Add food (Admin only)
- `DELETE /foods/{id}` - Delete food (Admin only)

### Categories
- `GET /categories` - Get all categories
- `POST /categories/AddCategory` - Add category (Admin only)
- `DELETE /categories/{id}` - Delete category (Admin only)

### Cart & Orders
- `GET /cart` - Get user cart
- `POST /cartitem/AddCartItem` - Add item to cart
- `PUT /cartitem/updateCartItem` - Update cart item
- `DELETE /cart/clear` - Clear cart
- `POST /orders/checkout` - Create order

**Full API documentation**: [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

---

## 🔐 Security

### Implemented Security Measures
- ✅ **BCrypt Password Hashing** - Passwords encrypted before storage
- ✅ **JWT Token Authentication** - Stateless authentication
- ✅ **HTTP Interceptor** - Auto-adds Bearer token to requests
- ✅ **CORS Protection** - Configured for specific origins
- ✅ **Role-Based Access** - Admin vs User permissions
- ✅ **Environment Variables** - Sensitive data not hardcoded
- ✅ **SQL Injection Protection** - Parameterized queries via JPA

### Security Best Practices
- Passwords never stored in plain text
- JWT tokens expire after 1 hour (configurable)
- CORS restricted to localhost in development
- Admin-only endpoints protected with role checks

---

## 🧪 Testing

### Manual Testing
1. Register a new user
2. Login with credentials
3. Browse menu items
4. Search for specific foods
5. Filter by category
6. Add items to cart
7. Modify cart quantities
8. Complete checkout
9. Verify order confirmation

### Test Credentials
After registration, use your own credentials.
Admin account must be created manually in database with role='ADMIN'.

---

## 🐛 Troubleshooting

### Common Issues

**Backend won't start:**
- Check MySQL is running: `mysql.server status`
- Verify database exists: `SHOW DATABASES;`
- Check .env file has correct password

**Frontend errors:**
- Clear npm cache: `npm cache clean --force`
- Delete node_modules and reinstall: `rm -rf node_modules && npm install`
- Check backend is running on port 8080

**401 Unauthorized:**
- Login again to get fresh token
- Check token expiration (default 1 hour)
- Verify token in localStorage

**CORS errors:**
- Ensure backend is running
- Check SecurityConfig.java has correct origins
- Clear browser cache

---

## 📈 Future Enhancements

- [ ] Order history page
- [ ] User profile management
- [ ] Real-time order tracking
- [ ] Food image uploads
- [ ] Rating and review system
- [ ] Payment gateway integration
- [ ] Email notifications
- [ ] Admin dashboard
- [ ] Analytics and reports
- [ ] Mobile app version

---

## 👨‍💻 Development

### Running in Development

**Backend with auto-reload:**
```bash
./mvnw spring-boot:run
```

**Frontend with live reload:**
```bash
ng serve
```

### Building for Production

**Backend:**
```bash
./mvnw clean package
java -jar target/Restaurant_System-0.0.1-SNAPSHOT.jar
```

**Frontend:**
```bash
ng build --configuration production
# Deploy dist/restaurant-frontend to web server
```

---

## 📝 License

This project is for educational purposes.

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📧 Contact

For questions or support:
- Check documentation in `/docs` folder
- Review [COMPLETE_SETUP_GUIDE.md](COMPLETE_SETUP_GUIDE.md)
- See [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

---

## 🎉 Acknowledgments

- Spring Boot team for excellent framework
- Angular team for modern frontend framework
- Community for libraries and tools

---

## 📊 Project Status

✅ **Production Ready**

- All features implemented
- All critical bugs fixed
- Security measures in place
- Complete documentation
- Ready for deployment

---

**⭐ If you find this project helpful, please star it! ⭐**

**Made with ❤️ using Spring Boot & Angular**
#   r e s t a u r a n t - o r d e r i n g - s y s t e m  
 