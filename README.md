# 🏋️ TrainingPro AI

O **TrainingPro AI** é uma API REST inteligente que gera treinos personalizados com base no perfil do usuário, utilizando Inteligência Artificial.

Desenvolvido com **Spring Boot**, o projeto integra a API da OpenAI para criar treinos adaptados ao esporte, nível e objetivos de cada usuário, oferecendo uma solução escalável e pronta para uso em aplicações fitness.

---

## 🚀 Funcionalidades Principais

* **Geração de Treinos Personalizados**
  O usuário informa esporte, objetivo, nível e duração, e a IA gera um treino sob medida.

* **Inteligência Artificial com OpenAI**
  Uso da API da OpenAI para criação de treinos dinâmicos e adaptáveis.

* **Autenticação Segura com JWT**
  Sistema completo de registro e login com autenticação stateless.

* **Filtros e Paginação**
  Listagem de treinos com filtros por esporte, objetivo e nível.

* **Documentação Interativa (Swagger)**
  Interface para testar os endpoints de forma prática.

* **Tratamento de Erros Padronizado**
  Respostas organizadas com códigos HTTP adequados.

---

## 🛠️ Tecnologias Utilizadas

### Backend

* Java 21
* Spring Boot 4.0.5
* Spring Security + JWT
* Spring Data JPA

### Banco de Dados

* PostgreSQL

### Integrações

* OpenAI API (GPT-4o-mini)

### Ferramentas

* Maven
* Lombok
* Swagger / OpenAPI 3
* Git/GitHub

---

## 📖 Como Funciona

### 1. Entrada de Dados 📊

O usuário fornece informações como esporte (ex: basquete, corrida, musculação), objetivo (ex: hipertrofia, emagrecimento, resistência), nível (iniciante, intermediário, avançado) e duração do treino.

---

### 2. Geração do Treino 🧠

A API envia os dados para a OpenAI, que processa e retorna um treino totalmente personalizado.

---

### 3. Acesso ao Treino 📱

O treino é retornado via API e pode ser consumido por qualquer frontend (web ou mobile).

---

## 🌟 Diferenciais

* **Personalização Total com IA** — Cada treino é único e adaptado ao usuário.
* **Escalabilidade** — Estrutura pronta para crescer como micro-SaaS.
* **Arquitetura Limpa** — Organização em camadas bem definida.
* **Segurança Robusta** — Autenticação via JWT stateless.
* **Flexibilidade** — Suporte a qualquer modalidade esportiva.

---

## 📦 Estrutura do Projeto

    src/main/java/com/eric/apitraining/
    │
    ├── client/        # Integração com OpenAI
    ├── config/        # Configurações (Swagger, etc)
    ├── controller/    # Endpoints REST
    ├── dto/           # Objetos de transferência de dados
    ├── entity/        # Entidades JPA
    ├── exception/     # Tratamento global de erros
    ├── repository/    # Interfaces JPA
    ├── security/      # JWT e configuração de segurança
    └── service/       # Regras de negócio

---

## ⚙️ Como Executar Localmente

### Pré-requisitos

* Java 17+
* Maven
* PostgreSQL
* Chave da OpenAI

---

### 1. Clone o repositório

```bash
git clone https://github.com/ericpatricio23/trainingpro-api-.git
cd trainingpro-api-
```

---

### 2. Configure as variáveis de ambiente

Crie o arquivo `src/main/resources/application-local.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

openai.api.key=sua_chave_openai

jwt.secret=seu_jwt_secret
jwt.expiration=86400000
```

> ⚠️ Este arquivo **não deve ser versionado**.

---

### 3. Execute o projeto

```bash
mvn spring-boot:run
```

---

### 4. Acesse o Swagger

```
http://localhost:8080/swagger-ui.html
```

---

## 🔐 Autenticação

A API utiliza **JWT** para proteger rotas.

1. `POST /auth/register` → Criar usuário
2. `POST /auth/login` → Obter token
3. No Swagger → Clique em **Authorize**
4. Informe: `Bearer seu_token_aqui`

---

## 💰 Uso da API da OpenAI (Importante)

Este projeto utiliza a API da OpenAI para geração de treinos personalizados.

> ⚠️ **Atenção:** A API da OpenAI é um serviço pago. Para utilizar a funcionalidade de geração de treinos, é necessário criar uma conta na OpenAI, gerar uma chave de API e adicionar créditos à conta. Sem créditos disponíveis, a geração de treinos com IA **não funcionará**.

---

## 🧪 Modo de Teste (Sem Custos)

Caso você não possua créditos na OpenAI, você pode:

* Testar apenas os endpoints de autenticação (registro/login)
* Testar listagem de treinos (caso haja dados mockados no banco)
* Implementar um **mock da resposta da IA** no projeto para simular os treinos

---

## Sempre Verifique

Se a API não estiver funcionando, verifique:

* Chave da OpenAI inválida
* Sem créditos na conta
* Limite de uso atingido
* Problemas de conexão

