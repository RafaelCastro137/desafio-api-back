# Desafio API - Backend Spring Boot

Este projeto é a API backend do desafio técnico Full Stack. Foi desenvolvido com Spring Boot e expõe endpoints REST para cadastro, listagem, edição e exclusão de usuários. A API é consumida por um frontend Angular disponível em:  
➡️ [`desafio-api-front`](https://github.com/RafaelCastro137/desafio-api-front)

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Bean Validation (Jakarta)
- H2 Database (em memória)
- Maven
- JUnit 5
- JaCoCo (cobertura de testes)

## 🔧 Funcionalidades da API

- ✅ Cadastro de usuários
- ✅ Edição de usuários
- ✅ Listagem de todos os usuários
- ✅ Exclusão de usuário por ID
- ✅ Validações com Bean Validation
- ✅ Mensagens de erro claras e específicas
- ✅ Respostas com `ResponseEntity`
- ✅ Testes unitários da camada de repositório

## 📦 Endpoints

| Método | Endpoint     | Descrição                      |
|--------|--------------|-------------------------------|
| POST   | `/`          | Cadastrar novo usuário        |
| GET    | `/`          | Listar todos os usuários      |
| PUT    | `/{id}`      | Editar usuário existente      |
| DELETE | `/{id}`      | Excluir usuário por ID        |

### Exemplo de JSON

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456"
}
