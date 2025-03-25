# 🛠️ API REST de Pedidos com Spring Boot

Este projeto é uma API REST desenvolvida com **Spring Boot**, utilizada para demonstrar conceitos de **CRUD (Create, Read, Update, Delete)** e persistência de dados com **Spring Data JPA**.

## 📌 **Tecnologias Utilizadas**
- Java 17+
- Spring Boot
- Spring Data JPA
- Banco de dados H2 (em memória)
- Maven
- Postman (para testes de API)

---

## 🚀 **Como Rodar o Projeto Localmente**
### **1️⃣ Clonar o Repositório**
```sh
git clone https://github.com/Natan-TI/fiap-checkpoint1
cd fiap-checkpoint1

2️⃣ Configurar o Ambiente
Garanta que você tem o Java 17+ e o Maven instalados.
Se precisar instalar o Maven, siga a documentação oficial.

🔄 Persistência dos Dados
Agora o banco de dados H2 foi configurado para ser persistente entre reinicializações. Isso significa que os produtos cadastrados não serão apagados ao reiniciar a aplicação.

📌 Configuração do Banco de Dados no application.properties
# Configuração do H2 (banco em memória)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Hibernate mantém os dados entre reinícios
spring.jpa.hibernate.ddl-auto=update

# Console do H2 habilitado
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

📥 Carregamento Inicial de Dados com DataLoader
Para garantir que o banco de dados tenha um conjunto inicial de produtos ao iniciar a aplicação, utilizamos um DataLoader.

📌 Implementação do DataLoader.java
package br.com.fiap.checkpoint1.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.repository.PedidoRepository;

@Configuration
public class DataLoader {
	
	@Bean
    CommandLineRunner carregarBanco(PedidoRepository pedidoRepository) {
        return args -> {
        	pedidoRepository.deleteAll();

            List<Pedido> pedidos = List.of(
                new Pedido(null, "Natan Santos", null, 500.0),
                new Pedido(null, "Kayky Paschoal", null, 220.5),
                new Pedido(null, "João Pedro", null, 150.0)
    		);

            pedidoRepository.saveAll(pedidos);

            // Confirmação no console
            long total = pedidoRepository.count();
            System.out.println("Total de produtos no banco: " + total);
        };
    }
}

3️⃣ Rodar o Projeto
Para iniciar o servidor, execute:

mvn spring-boot:run
A aplicação será iniciada em http://localhost:8080 🚀

🛠️ Endpoints da API
Aqui estão os principais endpoints da API e como testá-los no Postman ou cURL.

🔹 1. Listar Todos os Pedidos
📌 GET /pedidos
curl -X GET http://localhost:8080/pedidos

🔹 2. Buscar Pedido por ID
📌 GET /pedidos/{id}
curl -X GET http://localhost:8080/pedidos/1

🔹 3. Criar um Novo Pedido
📌 POST /pedidos 📌 Body (JSON):
{
  "clienteNome": "Natan Santos",
  "valorTotal": 500.0
}
curl -X POST http://localhost:8080/pedidos -H "Content-Type: application/json" -d '{"clienteNome": "Natan Santos", "valorTotal": 500.0}'

🔹 4. Atualizar um Pedido
📌 PUT /pedidos/{id} 📌 Body (JSON):
{
  "clienteNome": "Kayky Paschoal",
  "valorTotal": 220.5
}
curl -X PUT http://localhost:8080/pedidos/1 -H "Content-Type: application/json" -d '{"clienteNome": "Kayky Paschoal", "valorTotal": 220.5}'

🔹 5. Excluir um Pedido
📌 DELETE /pedidos/{id}
curl -X DELETE http://localhost:8080/pedidos/1

🗄️ Acessar o Banco de Dados H2
O projeto usa H2 Database para armazenar os dados temporariamente.

Passos para acessar o banco de dados H2:
1️⃣ Inicie a aplicação (mvn spring-boot:run).

2️⃣ Acesse no navegador:
http://localhost:8080/h2-console

3️⃣ Configuração de Acesso:

JDBC URL: jdbc:h2:mem:testdb

Usuário: sa

Senha: (deixe em branco)

4️⃣ Execute a consulta para visualizar os pedidos:
SELECT * FROM PEDIDOS;

👨‍🏫 Sobre o Projeto
Este projeto faz parte das aulas de Desenvolvimento Web com Spring Boot, com o objetivo de ensinar os alunos a:

✅ Criar e consumir APIs REST com Spring Boot
✅ Utilizar Spring Data JPA para persistência de dados
✅ Configurar um banco de dados H2 em memória
✅ Carregar dados iniciais com um DataLoader