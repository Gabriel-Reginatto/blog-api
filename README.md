# Blog-API

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-blue?logo=springsecurity&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-4.0.5-brightgreen?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18.3-blue?logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green?logo=swagger&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-3.0-green)
![License](https://img.shields.io/badge/License-Apache%202.0-red?logo=apache&logoColor=white)

**API REST para gerenciamento de conteúdo de blog com autenticação JWT, autorização por roles (USER/ADMIN), refresh token, HATEOAS e documentação interativa Swagger.**

</div>

---
Esta API REST centraliza todo o gerenciamento de conteúdo de um blog, oferecendo:

- CRUD completo de usuários, posts, categorias e comentários
- Autenticação JWT (Access Token + Refresh Token)
- Autorização baseada em roles (USER / ADMIN)
- Paginação e ordenação dinâmica
- Validação de dados
- Tratamento global de exceções
- Documentação interativa com Swagger/OpenAPI
- Criptografia de senha
- Arquitetura em camadas

# Como executar localmente

#### 1. Clone o repositório

```bash
git clone https://github.com/Gabriel-Reginatto/blog-api.git
cd blog-api
```
#### 2. Crie o banco de dados

```
CREATE DATABASE blog-api;
```
#### 3. Configure as variáveis de ambiente
```
# Banco de Dados
DB_URL=jdbc:postgresql://localhost:5432/blog-api
DB_USERNAME=postgres
DB_PASSWORD=sua_senha_aqui

# JWT
JWT_SECRET=3c5e8f2a9d1b7e4f6a8c0d2e4f6a8c0d2e4f6a8c0d2e4f6a8c0d2e4f6a8c0d
JWT_EXPIRATION=3600000
```
#### 4. Configure o application.yml
```
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    open-in-view: false

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION}

server:
  port: 8080
```
#### 5. Rodando a aplicação
 - Para rodar a aplicação, utilize o código o código abaixo
```
# Linux / Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

# Documentação
A documentação completa e interativa da API está disponível através do Swagger UI.

**Acesse em:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Após iniciar a aplicação, basta clicar no link acima para visualizar:

- Todos os endpoints disponíveis
- Parâmetros de cada requisição
- Exemplos de requisição e resposta
- Schemas dos DTOs

---
