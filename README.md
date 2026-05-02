# Blog API

API REST para gerenciamento de conteúdo de blog com suporte a usuários, posts, categorias e comentários. Desenvolvida com Spring Boot e seguindo os princípios REST, incluindo HATEOAS para navegação entre recursos.

---

## Tecnologias utilizadas

| Tecnologia | Versão |
|------------|--------|
| Java | 17 |
| Spring Boot | 4.0.5 |
| Spring Data JPA | - |
| Spring HATEOAS | - |
| SpringDoc OpenAPI | 3.0.2 |
| PostgreSQL | - |
| MapStruct | 1.5.5.Final |
| Maven | - |

---

## Funcionalidades

- CRUD completo de Usuários, Posts, Categorias e Comentários
- Paginação e ordenação dinâmica
- Relacionamento entre entidades (posts por usuário, comentários por post)
- Documentação interativa com Swagger UI
- Navegação HATEOAS entre recursos

---

## Arquitetura e boas práticas

- **Camadas bem definidas:** Controller, Service, Repository
- **DTOs imutáveis:** Utilização de `record` do Java 17 para contratos de entrada e saída
- **Tratamento global de exceções:** `@RestControllerAdvice` com respostas padronizadas
- **Validação declarativa:** Bean Validation com mensagens personalizadas
- **Versionamento da API:** `/api/v1/`
- **Separação de responsabilidades:** HATEOAS implementado com `PagedResourcesAssembler` e `RepresentationModelAssembler`

---

## Segurança

As credenciais do banco de dados são protegidas com variáveis de ambiente, sem exposição de informações sensíveis no código-fonte.

---

## Como executar o projeto

### Pré-requisitos

- Java 17
- PostgreSQL
- Maven

### Configuração

1. Clone o repositório:

git clone https://github.com/seuusuario/blog-api.git
cd blog-api

2. Configure as variáveis de ambiente:

DB_URL=jdbc:postgresql://localhost:5432/blog-api
DB_USERNAME=postgres
DB_PASSWORD=sua_senha

3. Execute a aplicação:

./mvnw spring-boot:run

4. Acesse a documentação Swagger:

http://localhost:8080/swagger-ui.html

Em andamento
Autenticação e autorização com Spring Security + JWT

Testes automatizados (unitários e de integração)

Deploy em ambiente de produção

Licença
Este projeto está sob a licença Apache 2.0. Consulte o arquivo LICENSE para mais informações.
