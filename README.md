# Hibernate Demo

This project demonstrates the implementation of user authentication and authorization using Spring Boot, Spring Security, Hibernate, and JWT.

## Features

- User Sign Up: Allows users to create new accounts with unique usernames and email addresses.
- User Sign In: Provides authentication for registered users.
- Role-based Access Control: Differentiates between user and admin roles, granting access to specific endpoints based on the user's role.
- JWT Authentication: Uses JSON Web Tokens (JWT) for secure authentication and authorization.
- Error Handling: Implements custom error handling for unauthorized access and other exceptions.

## Technologies Used

- Spring Boot
- Spring Security
- Hibernate
- JSON Web Tokens (JWT)
- RESTful API
- Maven

## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/hibernatedemo.git

   
   cd hibernatedemo
   
   
   mvn spring-boot:run
   
## Usage
   
##  Sign Up:

  Endpoint: POST /api/auth/signup
  Request Body:

   {
  "username": "yourusername",
  "email": "youremail@example.com",
  "password": "yourpassword",
  "role": ["user"] // or "admin" for admin role
   }
   
##  Sign In:

  Endpoint: POST /api/auth/signin
  Request Body:
   {
  "username": "yourusername",
  "password": "yourpassword"
   }
   
   Access User-restricted Endpoint:

  Endpoint: GET /api/test/user
  Required Role: ROLE_USER
  Access Admin-restricted Endpoint:

  Endpoint: GET /api/test/admin
  Required Role: ROLE_ADMIN
   
   