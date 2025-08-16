# ForumHub
Uma API Rest para um fórum de discussão, desenvolvido em Java

## Tecnologia
\- Java 17  
\- Spring Boot (Web, Security, Validation, Data JPA)  
\- Docker  
\- Flyway  
\- JWT (auth0 java\-jwt)  
\- Maven  
\- BCrypt


## Authentication
Login issues a JWT (HMAC256). Secret loaded from `api.security.token.secret`. Token expiry: 2 hours. Add a future filter/interceptor to validate JWT on protected endpoints (not yet implemented in this code snapshot).

## Pré-requisitos
\- Java 17  
\- PostgreSQL running locally (default url: `jdbc:postgresql://localhost:5432/forum_db`)  
\- Maven 3\.9\+
\- Docker

## How to run
1. Clone the repository:
2. Navigate to the project directory:
   ```bash
   cd forumhub
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run database:
   ```bash
   docker compose up
   ```
5. Run the application:
   ```bash
    mvn spring-boot:run
    ```


