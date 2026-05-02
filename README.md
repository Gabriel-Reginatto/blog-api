Blog API
API REST para gerenciamento de conteúdo de blog com suporte a usuários, posts, categorias e comentários. Desenvolvida com Spring Boot e seguindo os princípios REST, incluindo HATEOAS para navegação entre recursos.

Tecnologias utilizadas
Java 17

Spring Boot 4.0.5

Spring Data JPA

Spring HATEOAS

SpringDoc OpenAPI (Swagger)

PostgreSQL

MapStruct

Maven

Funcionalidades
CRUD completo de Usuários, Posts, Categorias e Comentários

Paginação e ordenação dinâmica

Relacionamento entre entidades (posts por usuário, comentários por post)

Documentação interativa com Swagger UI

Navegação HATEOAS entre recursos

Arquitetura e boas práticas
Camadas bem definidas: Controller, Service, Repository

DTOs imutáveis: utilização de record do Java 17 para contratos de entrada e saída

Tratamento global de exceções: RestControllerAdvice com respostas padronizadas

Validação declarativa: Bean Validation com mensagens personalizadas

Versionamento da API: /api/v1/

Separação de responsabilidades: HATEOAS implementado com PagedResourcesAssembler e RepresentationModelAssembler

Segurança
As credenciais do banco de dados são protegidas com variáveis de ambiente, sem exposição de informações sensíveis no código-fonte.

Como executar o projeto
Pré-requisitos
Java 17

PostgreSQL

Maven

Configuração
Clone o repositório:

bash
git clone https://github.com/seuusuario/blog-api.git
Configure as variáveis de ambiente:

env
DB_URL=jdbc:postgresql://localhost:5432/blog-api
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
Execute a aplicação:

bash
./mvnw spring-boot:run
Acesse a documentação Swagger:

text
http://localhost:8080/swagger-ui.html
Estrutura de pacotes
text
br/com/blog/api/
├── api/                    # Camada de apresentação
│   ├── controller/         # Endpoints REST
│   ├── assembler/          # Montagem de links HATEOAS
│   ├── docs/               # Interfaces de documentação Swagger
│   └── dto/                # Objetos de transferência de dados
├── core/                   # Camada de domínio
│   ├── domain/             # Entidades de negócio
│   ├── repository/         # Interfaces JPA
│   ├── service/            # Regras de negócio
│   └── mapper/             # Mapeamento com MapStruct
└── infrastructure/         # Camada técnica
    ├── annotation/         # Anotações customizadas
    ├── config/             # Configurações
    └── exception/          # Tratamento de erros
Endpoints principais
Usuários
Método	Endpoint	Descrição
GET	/api/v1/users	Lista usuários (paginado)
GET	/api/v1/users/{id}	Busca usuário por ID
POST	/api/v1/users	Cria novo usuário
PUT	/api/v1/users/{id}	Atualiza usuário
DELETE	/api/v1/users/{id}	Remove usuário
Posts
Método	Endpoint	Descrição
GET	/api/v1/posts	Lista posts (paginado)
GET	/api/v1/posts/{id}	Busca post por ID
POST	/api/v1/posts	Cria novo post
PUT	/api/v1/posts/{id}	Atualiza post
DELETE	/api/v1/posts/{id}	Remove post
Categorias
Método	Endpoint	Descrição
GET	/api/v1/categories	Lista categorias (paginado)
GET	/api/v1/categories/{id}	Busca categoria por ID
POST	/api/v1/categories	Cria nova categoria
PUT	/api/v1/categories/{id}	Atualiza categoria
DELETE	/api/v1/categories/{id}	Remove categoria
Comentários
Método	Endpoint	Descrição
GET	/api/v1/comments/{id}	Busca comentário por ID
GET	/api/v1/comments/post/{postId}	Lista comentários de um post
POST	/api/v1/comments/post/{postId}	Cria comentário em um post
DELETE	/api/v1/comments/{id}	Remove comentário
Em andamento
Autenticação e autorização com Spring Security + JWT

Testes automatizados (unitários e de integração)

Deploy em ambiente de produção

Licença
Este projeto está sob a licença Apache 2.0. Consulte o arquivo LICENSE para mais informações.

Autor
Seu Nome - https://github.com/seuusuario

Links úteis
Documentação Swagger: http://localhost:8080/swagger-ui.html
