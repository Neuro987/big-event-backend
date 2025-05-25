# Big Event - Article Management System

A RESTful article management system built with Spring Boot, featuring user authentication, article publishing, and cloud file storage.

## 🚀 Features

- **User Management**: Registration, login, profile updates
- **Article Management**: Create, read, update, delete articles with pagination
- **Category Management**: Organize articles by categories
- **File Upload**: Integration with Tencent Cloud Object Storage (COS)
- **Security**: JWT-based authentication with Redis token management
- **Data Validation**: Custom validation annotations and Bean Validation

## 🛠️ Tech Stack

- **Backend**: Spring Boot 3.4.4, Spring MVC
- **Database**: MySQL with MyBatis ORM
- **Authentication**: JWT + Redis
- **Cloud Storage**: Tencent Cloud COS
- **Build Tool**: Maven
- **Java Version**: 17

## 📦 Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data Redis
- Spring Boot Starter Validation
- MyBatis Spring Boot Starter
- MySQL Connector
- JWT (java-jwt)
- PageHelper (pagination)
- Tencent COS SDK
- Lombok

## 🚦 Getting Started

### Prerequisites

- Java 17+
- MySQL 8.0+
- Redis
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Neuro987/big-event-backend.git
   cd big-event
   ```

2. **Configure the database**
   - Create a MySQL database named `big_event`
   - Update database credentials in `src/main/resources/application.yml`

3. **Configure Redis**
   - Ensure Redis is running on localhost:6379
   - Update Redis configuration in `application.yml` if needed

4. **Configure Tencent COS (Optional)**
   - Update COS credentials in `application.yml` for file upload functionality

5. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

   Or on Windows:
   ```cmd
   mvnw.cmd spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📚 API Endpoints

### User Management
- `POST /user/register` - User registration
- `POST /user/login` - User login
- `GET /user/userInfo` - Get user information
- `PUT /user/update` - Update user profile
- `PATCH /user/updateAvatar` - Update user avatar
- `PATCH /user/updatePwd` - Update password

### Article Management
- `POST /article` - Create article
- `GET /article` - Get articles with pagination
- `PUT /article` - Update article
- `GET /article/detail` - Get article details
- `DELETE /article` - Delete article

### Category Management
- `POST /category` - Create category
- `GET /category` - Get all categories
- `GET /category/detail` - Get category details
- `PUT /category` - Update category
- `DELETE /category` - Delete category

### File Upload
- `POST /upload` - Upload file to cloud storage

## 🔒 Authentication

The API uses JWT tokens for authentication. Include the token in the `Authorization` header:

```
Authorization: <your-jwt-token>
```

Tokens are stored in Redis and have a 24-hour expiration time.

## 🏗️ Project Structure

```
src/main/java/com/learn/bigevent/
├── controller/     # REST controllers
├── service/        # Business logic layer
├── mapper/         # MyBatis mappers
├── pojo/          # Entity classes
├── utils/         # Utility classes
├── config/        # Configuration classes
├── interceptors/  # Request interceptors
├── exception/     # Exception handlers
├── validation/    # Custom validators
└── anno/          # Custom annotations
```

## 🧪 Testing

Run the test suite:

```bash
./mvnw test
```

Test classes include:
- JWT functionality tests
- Redis integration tests
- Thread safety tests

## 📝 Configuration

Key configuration properties in `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/big_event
    username: your_username
    password: your_password
  data:
    redis:
      host: localhost
      port: 6379

tencent.cos:
  secret-id: your_secret_id
  secret-key: your_secret_key
  bucket-name: your_bucket_name
  region: your_region
```

