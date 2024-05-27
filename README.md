# LANCHONETE-API

Este projeto implementa um sistema de lanchonete com pedidos e pagamentos utilizando arquitetura hexagonal em Java com Spring Boot. 
O sistema permite aos clientes selecionar produtos e realizar pagamentos via Pix do MercadoPago, acompanhar o progresso de seus pedidos e oferece funcionalidades administrativas para gerenciamento de clientes, fila de pedidos e produtos.

Este projeto encontra-se na v.1, visando entrega na primeira fase do MBA Engenharia de Software da FIAP/SP.

## Pricipais Funcionalidades

### Cliente
- Identificação via CPF ou cadastro com nome e email
- Seleção de produtos por categoria (Lanche, Acompanhamento, Bebida, Sobremesa)
- Visualização de nome, descrição e preço dos produtos

### Pedido
- Criação e finalização de pedidos
- Acompanhamento do status do pedidoDomain (Recebido, Em preparação, Pronto, Finalizado)
- Notificação ao Cliente quando o Pedido está pronto para retirada

### Pagamento
- Integração com MercadoPago para pagamentos via Pix

### Administrativo
- Gerenciamento de clientes
- Gerenciamento de produtos
- Acompanhamento dos pedidos em andamento

## Estrutura do Projeto

O projeto pretendeu seguir a arquitetura hexagonal, dividindo o código em camadas bem definidas.

## Instalação

### Pré-requisitos
- Java 17 ou superior
- Docker e Docker Compose
- MySQL

### Passos para instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/seuprojeto.git
    cd lanchonete-api
    ```

2. Configure o banco de dados no `application-dev.properties`:
    ```properties
    spring.profiles.active=dev
    spring.datasource.url=jdbc:mysql://localhost:3306/lanchonete-api
    spring.datasource.username=SEU_USUARIO_ROOT
    spring.datasource.password=SUA_SENHA
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build e execute a aplicação usando Docker:
    ```bash
    docker-compose up --build
    ```

4. Acesse o Swagger para interagir com as APIs:
    ```
    http://localhost:8080/swagger-ui.html
    ```
5. Caso prefira, pode ser executado localmente, com dados mock do BD no profile de teste com h2 Database, configurando o `application-test.properties` alterarando a linha no `application.properties`:
   ```properties
    spring.profiles.active=test
    ```
6. Para integração com o MercadoPago é necessário criar um token, siga as instruções no link:

   https://www.mercadopago.com.br/developers/pt/reference/oauth/_oauth_token/post


7. Após criado o token, altere a linha no `application.properties`:
   ```properties
    mercadopago.access_token=SEU_ACCESS_TOKEN
    ```