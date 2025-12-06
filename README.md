# MyBlog-Backend

A modern, feature-rich blog backend system built with Spring Boot 3.2.5 and Java 21. This system provides comprehensive blog management capabilities with role-based access control, JWT authentication, and cloud integration.

## 🚀 Features

- **Multi-Module Architecture**: Modular design with separate modules for framework, admin, and guest functionalities
- **Secure Authentication**: JWT-based authentication with Spring Security integration
- **Role-Based Access Control**: Different access levels for administrators and guests
- **Content Management**: Full CRUD operations for articles, categories, tags, and comments
- **Cloud Integration**: AWS SDK integration for file uploads and cloud storage
- **Caching**: Redis caching strategy for improved performance
- **API Documentation**: OpenAPI 3.0 documentation with Swagger UI
- **Database Persistence**: JPA/Hibernate with comprehensive entity relationships
- **Native Compilation**: GraalVM support for native image generation

## 📋 Requirements

- Java 21+
- Maven 3.6+
- MySQL 5.5+ or compatible
- Redis (for caching)
- MinIO (for object storage)

## 🏗️ Project Structure

```
MyBlog-Backend/
├── myblog-admin/          # Admin module - backend management APIs
├── myblog-framework/      # Core framework module
├── myblog-guest/          # Guest module - public APIs
├── sql/                   # Database schema and migrations
└── pom.xml               # Parent Maven configuration
```

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.2.5, Spring Security, Spring Data JPA
- **Database**: MySQL with Hibernate 6.4.4
- **Authentication**: JWT (JJWT 0.12.5)
- **Cache**: Redis
- **Documentation**: SpringDoc OpenAPI 2.5.0
- **OSS**: MinIO with AWS SDK 2.25.49
- **Build**: Maven, GraalVM Native Image
- **Java**: 21

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/KarigenBrown/MyBlog-Backend.git
cd MyBlog-Backend
```

### 2. Database Setup
Import the SQL files from the `sql/` directory:
```bash
# Import all SQL files in order
mysql -u your_username -p myblog < sql/article.sql
mysql -u your_username -p myblog < sql/user.sql
mysql -u your_username -p myblog < sql/category.sql
mysql -u your_username -p myblog < sql/tag.sql
mysql -u your_username -p myblog < sql/article_tag.sql
mysql -u your_username -p myblog < sql/comment.sql
mysql -u your_username -p myblog < sql/link.sql
mysql -u your_username -p myblog < sql/menu.sql
```

### 3. Build the Project
Execute the build command in the parent project directory (where `pom.xml` is located):
```bash
mvn package assembly:single
```

### 4. Configuration
Configure your application properties with your database, Redis, and AWS credentials.

### 5. Run the Application
```bash
# Run specific module
java -jar myblog-admin/target/myblog-admin-1.0-SNAPSHOT.jar
# or
java -jar myblog-guest/target/myblog-guest-1.0-SNAPSHOT.jar
```

## 📚 API Documentation

Once the application is running, you can access the API documentation at:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## 🔧 Development

### Build with Native Image
For performance optimization, you can build a native image using GraalVM:
```bash
mvn -Pnative package
```

### Modules Overview

- **myblog-framework**: Core utilities, security configurations, and shared components
- **myblog-admin**: Administrative endpoints for content management and user administration
- **myblog-guest**: Public-facing APIs for reading articles and public content

## 📊 Database Schema

The system uses the following main entities:
- **Articles**: Blog posts with content, metadata, and relationships
- **Users**: User management with role-based permissions
- **Categories**: Hierarchical content organization
- **Tags**: Flexible content tagging system
- **Comments**: User engagement through comments
- **Links**: Blog roll and external links management
- **Menus**: Navigation menu configuration

## 🔐 Security

- JWT-based stateless authentication
- Role-based access control (Admin/User/Guest)
- Password encryption and secure session management
- API rate limiting and request validation

## 🌐 API Endpoints

### Admin Module (`/api/admin/*`)
- Article management
- User and role management
- Category and tag management
- Comment moderation
- System configuration

### Guest Module (`/api/guest/*`)
- Article browsing and search
- Public content access
- Comment submission
- Tag and category browsing
