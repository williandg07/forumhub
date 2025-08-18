# Forum Hub - API REST

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-green)
![JWT](https://img.shields.io/badge/JWT-0.11.5-blue)
![H2](https://img.shields.io/badge/H2-Database-lightblue)
![Maven](https://img.shields.io/badge/Maven-Build-red)

Uma API REST completa para sistema de fórum desenvolvida com Java Spring Boot.

## 🚀 Funcionalidades

- **Autenticação JWT**: Sistema de login/registro com tokens JWT
- **CRUD de Tópicos**: Criar, listar, atualizar e deletar tópicos
- **Validações**: Validação de dados de entrada
- **Paginação**: Listagem paginada de tópicos
- **Segurança**: Proteção de endpoints com Spring Security
- **Banco H2**: Banco de dados em memória para desenvolvimento

## 🛠️ Tecnologias

- Java 21
- Spring Boot 3.2.5
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- H2 Database
- Maven

## 📋 Pré-requisitos

- Java 21+
- Maven 3.6+
- IDE (IntelliJ IDEA recomendado)

## 🔧 Como executar

### No IntelliJ IDEA:

1. **Importar projeto**: File → Open → Selecione a pasta do projeto
2. **Aguardar Maven**: Deixe o Maven baixar as dependências
3. **Configurar JDK**: Project Structure → Project → SDK = Java 21
4. **Executar**: Clique em ▶️ na classe `ForumHubApplication`

### Via terminal:

```bash
# Clone o repositório
git clone <url-do-repo>
cd forum-hub

# Execute
mvn spring-boot:run
```

## 📖 Como usar

### 1. Registrar usuário
```bash
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
    "username": "usuario1",
    "email": "usuario1@email.com",
    "password": "123456"
}
```

### 2. Fazer login
```bash
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
    "username": "usuario1",
    "password": "123456"
}
```

### 3. Criar tópico (use o token do login)
```bash
POST http://localhost:8080/api/topics
Content-Type: application/json
Authorization: Bearer SEU_TOKEN_AQUI

{
    "title": "Como usar Spring Boot?",
    "message": "Preciso de ajuda com Spring Boot",
    "course": "Java"
}
```

### 4. Listar tópicos
```bash
GET http://localhost:8080/api/topics
```


## 🏗️ Estrutura do Projeto

```
src/main/java/com/forumhub/
├── controller/     # Controllers REST
├── dto/           # Data Transfer Objects  
├── entity/        # Entidades JPA
├── repository/    # Repositórios Spring Data
├── service/       # Lógica de negócio
└── config/        # Configurações
```

## ✅ Funcional e Testado

✅ Compilação sem erros  
✅ Dependências compatíveis  
✅ JWT funcionando  
✅ CRUD completo  
✅ Validações implementadas  
✅ Paginação funcionando  
✅ Segurança configurada  

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Add nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT.
