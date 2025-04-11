
# 🗂️ AnotaAi - Backend

Este é o backend da aplicação **AnotaAi**, desenvolvido para o Desafio 03 do programa de bolsas **Trilhas Inova Maranhão**.  
A aplicação disponibiliza APIs para autenticação, registro de usuários e gerenciamento de dados dos participantes.

---

## 🚀 Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Security  
- JWT (JSON Web Token)  
- MySQL  
- Maven  

---

## ⚙️ Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- Java 17 ou superior  
- Maven  
- MySQL em execução  

---

## 💻 Como rodar o projeto localmente

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/anotaai-backend.git
   ```

2. **Instale as dependências com o Maven:**
   ```bash
   mvn install
   ```

3. **Configure o banco de dados:**

   Crie um banco com o nome `anotaai` ou altere no arquivo `application.properties` a seguinte linha:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nomedobanco
   ```

   Altere também a senha do banco, se necessário:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=sua_senha
   ```

4. **Rode a aplicação.**  
   No log, será exibido um hash de senha, exemplo:

   ```
   $2a$10$dyGNUbEJXs7eti3ZAKEXy.qLf.Q2y4B35hdfxoZ./0Qyii7k7hge.
   ```

5. **Crie manualmente o primeiro usuário no banco de dados:**
   ```sql
   INSERT INTO pessoa (nome) VALUES ("adm");

   INSERT INTO usuario (login, pass, role, pessoa_id)
   VALUES ("adm", '$2a$10$ENz50sssUrS6xqY4.j1UsuFUT6GUyDqk5lH0UC.vaIZDCXQcOC2Se', 0, 1);
   ```
   > 💡 A senha padrão acima corresponde ao valor hash de `1234`.

---

## 📮 Endpoints disponíveis

### 🔑 Login

- **URL:** `POST /auth/login`

#### Requisição:
```json
{
  "login": "Vini12233",
  "pass": "eee@$fd2"
}
```

#### Respostas:

- **Erro:**
```json
{
  "erro": "Usuário ou senha inválido"
}
```

- **Sucesso:**
```json
{
  "id": 29,
  "login": "vini1234",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "role": "Participante"
}
```

> ⚠️ O token tem validade de 24 horas.

---

### 📝 Registro de Participante

- **URL:** `POST /auth/registro/Participante`

#### Requisição:
```json
{
  "id": null,
  "login": "vi22222",
  "pass": "12332W",
  "role": 1,
  "trilhaId": 1,
  "pessoa": {
    "nome": "Vinicius Guimarães dos Santos",
    "email": "vinigui@gmail.com",
    "telefone": "(99) 98486-6191",
    "cpf": "623.360.763-90",
    "sexo": 0,
    "dataNascimento": "2003-11-28"
  },
  "endereco": {
    "cep": "64018-280",
    "rua": "Rua São João do Piauí",
    "numero": "2773",
    "cidade": "Teresina",
    "estado": "Piauí"
  }
}
```

#### Respostas possíveis:

- **Login já existente:**
```json
{
  "erro": "login já existente"
}
```

- **Sucesso:**
```json
{
  "id": 30,
  "login": "vi22222",
  "role": "Participante",
  "pessoa": {
    "nome": "Raimnuendo fulando silca",
    "email": "vinigeeui@gmail.com",
    ...
  },
  "enabled": true,
  "authorities": [
    {
      "authority": "Participante"
    }
  ]
}
```

---

### 📂 Buscar dados do participante

- **URL:** `GET /trilha/getdados`

#### Exemplo de resposta:
```json
{
  "trilhaId": 4,
  "pessoa": {
    "nome": "Raimnuendo fulando silca",
    "dataNascimento": "2003-11-28T00:00:00.000+00:00",
    "cpf": "633.360.733-91",
    "sexo": 1,
    "email": "guimara333inicius@gmail.com",
    "telefone": "(99) 94486-6191"
  },
  "emdereco": {
    "cep": "64018-280",
    "rua": "",
    "nuemro": "2473",
    "cidade": "macapa",
    "estado": "PI"
  }
}
```

---

## 📌 Observações

- O primeiro usuário **deve ser criado manualmente** no banco de dados por segurança.
- A role `0` representa **Administrador**, e a `1` representa **Participante**.
- O token JWT retornado deve ser enviado em todas as requisições protegidas.

---

## 📫 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

---
