# Sistema de Pedido e Pagamento

Este projeto implementa um sistema de pedidos e pagamentos utilizando arquitetura hexagonal em Java com Spring Boot. O sistema permite aos clientes selecionar produtos, realizar pagamentos via QRCode do MercadoPago, acompanhar o progresso de seus pedidos e oferece funcionalidades administrativas para gerenciamento de clientes e produtos.

## Funcionalidades

### Cliente
- Identificação via CPF ou cadastro com nome e email
- Seleção de produtos (Lanche, Acompanhamento, Bebida, Sobremesa)
- Visualização de nome, descrição e preço dos produtos

### Pedido
- Criação e finalização de pedidos
- Acompanhamento do status do pedido (Recebido, Em preparação, Pronto, Finalizado)
- Notificação ao cliente quando o pedido está pronto para retirada

### Pagamento
- Integração com MercadoPago para pagamentos via QRCode

### Administrativo
- Gerenciamento de clientes (campanhas promocionais)
- Gerenciamento de produtos e categorias (nome, categoria, preço, descrição, imagens)
- Acompanhamento dos pedidos em andamento e tempo de espera

## Estrutura do Projeto

O projeto segue a arquitetura hexagonal, dividindo o código em camadas bem definidas:

src
└── main
├── java
│ └── com
│ └── seuprojeto
│ ├── application
│ │ └── service
│ │ ├── ClienteService.java
│ │ ├── FilaService.java
│ │ ├── PedidoService.java
│ │ ├── ProdutoService.java
│ │ └── FakeCheckoutService.java
│ ├── domain
│ │ ├── model
│ │ └── repository
│ ├── infrastructure
│ │ ├── persistence
│ │ │ └── jpa
│ │ └── adapter
│ │ ├── ClienteServiceImpl.java
│ │ ├── PedidoServiceImpl.java
│ │ ├── ProdutoServiceImpl.java
│ │ └── DBService.java
│ └── interface
│ ├── controller
│ └── dto
└── resources


## Instalação

### Pré-requisitos
- Java 11 ou superior
- Docker e Docker Compose
- MySQL

### Passos para instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/seuprojeto.git
    cd seuprojeto
    ```

2. Configure o banco de dados no `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/seuprojeto
    spring.datasource.username=root
    spring.datasource.password=root
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Configure o arquivo `Dockerfile` para construir a aplicação:
    ```Dockerfile
    # Use a imagem base do OpenJDK
    FROM openjdk:11-jdk

    # Defina o diretório de trabalho
    WORKDIR /app

    # Copie o arquivo JAR da aplicação para o contêiner
    COPY target/seuprojeto.jar app.jar

    # Exponha a porta que a aplicação irá rodar
    EXPOSE 8080

    # Comando para executar a aplicação
    ENTRYPOINT ["java", "-jar", "app.jar"]
    ```

4. Crie o arquivo `docker-compose.yml` no diretório raiz do projeto:
    ```yaml
    version: '3.8'
    services:
      app:
        image: openjdk:11-jdk
        container_name: pedido-app
        build:
          context: .
          dockerfile: Dockerfile
        ports:
          - "8080:8080"
        environment:
          SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/seuprojeto
          SPRING_DATASOURCE_USERNAME: root
          SPRING_DATASOURCE_PASSWORD: root
          SPRING_JPA_HIBERNATE_DDL_AUTO: update
        depends_on:
          - db

      db:
        image: mysql:8.0
        container_name: pedido-db
        environment:
          MYSQL_ROOT_PASSWORD: root
          MYSQL_DATABASE: seuprojeto
          MYSQL_USER: root
          MYSQL_PASSWORD: root
        ports:
          - "3306:3306"
        volumes:
          - db_data:/var/lib/mysql

    volumes:
      db_data:
    ```

5. Build e execute a aplicação usando Docker:
    ```bash
    docker-compose up --build
    ```

6. Acesse o Swagger para interagir com as APIs:
    ```
    http://localhost:8080/swagger-ui.html
    ```

## APIs Disponíveis

### Cliente
- `POST /clientes` - Cria um novo cliente
- `GET /clientes/{id}` - Obtém um cliente pelo ID
- `GET /clientes` - Lista todos os clientes
- `DELETE /clientes/{id}` - Deleta um cliente pelo ID

### Pedido
- `POST /pedidos` - Cria um novo pedido
- `GET /pedidos/{id}` - Obtém um pedido pelo ID
- `GET /pedidos` - Lista todos os pedidos
- `DELETE /pedidos/{id}` - Deleta um pedido pelo ID

### Produto
- `POST /produtos` - Cria um novo produto
- `GET /produtos/{id}` - Obtém um produto pelo ID
- `GET /produtos` - Lista todos os produtos
- `DELETE /produtos/{id}` - Deleta um produto pelo ID

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Faça commit das suas alterações (`git commit -am 'Adiciona nova feature'`)
4. Faça push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request


