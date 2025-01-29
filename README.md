# PRJ_BancoJaver

**PRJ_BancoJaver** é um projeto baseado em **microserviços bancários**, desenvolvido como parte de um desafio técnico. Ele conta com **arquitetura de microserviços**, comunicação via **Feign Client**, autenticação com **Spring Security**, validação avançada de formulários e um **frontend em React** para gerenciamento de clientes.

---

## 📌 Tecnologias Utilizadas

### **🛠 Backend**
- **Java 17** + **Spring Boot**
- **Spring Security** (Autenticação básica)
- **Spring Data JPA** (Persistência)
- **OpenFeign** (Comunicação entre microserviços)
- **Swagger/OpenAPI** (Documentação)
- **H2 Database** (Banco em memória para testes)
- **JUnit & Mockito** (Testes unitários)
- **Lombok** (Redução de boilerplate)

### **🌐 Frontend**
- **React.js** (UI)
- **Axios** (Consumo de API)
- **React Hook Form + Yup** (Validação de formulários)
- **Styled Components** (Estilização moderna)
- **Responsividade** (CSS Flexbox/Grid)

---

## ⚙️ Instalação e Configuração

### 🔹 1. Clonando o Repositório
```sh
git clone https://github.com/DevAnaBeatriz/PRJ_BancoJaver.git
cd PRJ_BancoJaver
```

### 🔹 2. Configurar e Rodar o **Backend**

#### **🔹 Executando o database-service**
1. **Entre na pasta do banco de dados**
   ```sh
   cd backend/database-service
   ```
2. **Rodar o serviço**
   ```sh
   mvn spring-boot:run
   ```

#### **🔹 Executando o client-service**
1. **Entre na pasta do client-service**
   ```sh
   cd backend/client-service
   ```
2. **Rodar o serviço**
   ```sh
   mvn spring-boot:run
   ```

3. **Acesse o Swagger para testar a API**
   - **Client Service**: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
   - **Database Service**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

### 🔹 3. Configurar e Rodar o **Frontend**
1. **Entre na pasta do frontend**
   ```sh
   cd frontend
   ```
2. **Instale as dependências**
   ```sh
   npm install
   ```
3. **Inicie a aplicação**
   ```sh
   npm start
   ```
4. **Acesse no navegador**
   - **URL**: [http://localhost:3000](http://localhost:3000)

---

## 🔑 Autenticação

O projeto usa **Spring Security** com autenticação básica:

- **Usuário:** `admin`
- **Senha:** `ana123`

Para acessar a API no **Postman** ou **Axios**, inclua essas credenciais na autenticação.

---

## 🛠️ Testes

### **Rodando os testes unitários**
```sh
mvn test
```

O projeto possui **testes automatizados** para os serviços e Feign Clients.

---

## 📌 Funcionalidades

✅ **Backend**
- CRUD de Clientes
- Comunicação entre microserviços via **Feign Client**
- Cálculo automático de **Score de Crédito**
- Validações de entrada via **Spring Validation**
- Documentação com **Swagger/OpenAPI**

✅ **Frontend**
- Interface **responsiva e moderna** em **React.js**
- Listagem, criação e edição de clientes
- Validação de formulários com **React Hook Form + Yup**
- Conexão com a API via **Axios**

---

## 📝 Melhorias Futuras

- 🔄 Implementar autenticação JWT
- 📈 Criar gráficos de análise de clientes no frontend
- 🚀 Melhorar cobertura de testes automatizados

---

## 🤝 Contribuição

1. Faça um **fork** do projeto
2. Crie uma **branch** para sua funcionalidade (`git checkout -b feature-nova`)
3. **Commit** suas alterações (`git commit -m "Adiciona nova funcionalidade"`)
4. Faça o **push** para a branch (`git push origin feature-nova`)
5. Abra um **Pull Request**

---
