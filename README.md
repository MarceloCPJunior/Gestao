
# API de Gestão Bancária

Este projeto é uma API REST para gestão bancária, que permite criar contas e realizar transações. Foi desenvolvido com foco em escalabilidade, facilidade de manutenção e deploy em nuvem.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Eclipse IDE (versão 06-2025)**
- **Apache Tomcat 10 (Jakarta EE 9+)**
- **Jersey (JAX-RS) para API REST**
- **Swagger/OpenAPI para documentação**
- **Hibernate + JPA (Jakarta Persistence)**
- **HikariCP para pool de conexões**
- **Banco de Dados: Neon Serverless PostgreSQL**

## 🗄️ Banco de Dados

O projeto utiliza o banco **Neon Serverless PostgreSQL**, com dois ambientes separados:

- ✅ **Homologação:**  
Passando o header:  
```
X-ENV: dev
```

- 🚀 **Produção:**  
Passando o header:  
```
X-ENV: prod
```

Caso o header não seja informado, o padrão será o ambiente de **homologação (dev)**.
Para o swagger, ambos os links são homologação, já que o swagger é um ambiente de teste.

## ☁️ Deploy

O deploy da aplicação foi realizado na AWS utilizando o serviço **Elastic Beanstalk**, que faz o gerenciamento da infraestrutura automaticamente.

## 📑 Documentação da API (Swagger)

A documentação interativa da API está disponível no link:

👉 [Acessar Swagger](http://gestao.us-east-1.elasticbeanstalk.com/swagger-ui/index.html)

> A documentação inclui detalhes dos endpoints, exemplos de request/response e os schemas dos JSONs.

---

## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/ngbilling/gestaobancaria/
│   │       ├── application/       # Configurações do Jersey e Swagger
│   │       ├── arquitetura/        # Classe base de recursos (Generic's)
│   │       ├── bo/                 # Regras de negócio (Business Objects)
│   │       ├── dto/                # Data Transfer Objects (dto's das entidades e response)
│   │       ├── interfaces/         # Interfaces
│   │       ├── model/              # Entidades JPA
│   │       ├── resources/          # Endpoints REST
│   │       └── util/               # Utilitários (Ex.: JPAUtil)
│   ├── resources/                  # Arquivos de configuração
│   │   ├── META-INF/persistence.xml  # Configurações de persistência
│   │   ├── openapi-configuration.yaml  # Configurações do swagger
│   │   ├── db-dev.properties       # propriedades do banco de dados de homologação
│   │   └── db-prod.properties      # propriedades do banco de dados de produção
│   └── webapp/
│       ├── swagger-ui/             # Swagger UI customizado
│       └── WEB-INF/web.xml         # Configuração do Tomcat
```

---

## ⚙️ Como Executar Localmente

Este projeto foi desenvolvido utilizando o **Eclipse IDE (versão 06-2025)** com suporte a projetos **Web com Maven**.

### ✅ Pré-requisitos

- Apache **Tomcat 10** configurado no Eclipse.
- Plugin **Maven** habilitado no Eclipse.

### ✅ Passos para rodar localmente

1. Clone o projeto para sua máquina:
```bash
git clone https://github.com/MarceloCPJunior/Gestao.git
```

2. No Eclipse, importe o projeto como:
```
File → Import → Maven → Existing Maven Projects
```

3. Certifique-se que o projeto está com os **Project Facets** corretos:
- Dynamic Web Module: **4.0**
- Java: **17**
- JavaScript: **1.0**
- JPA: **3.1**

Se necessário, clique com o botão direito no projeto → **Properties → Project Facets** para ajustar.

4. Adicione o projeto ao Tomcat:
```
Servers → Botão direito no Tomcat → Add and Remove → Selecione o projeto → Add
```

5. Inicie o Tomcat pelo próprio Eclipse.

6. Acesse a aplicação:
```
http://localhost:8080/Gestao/swagger-ui/index.html
```

---

## 💡 Observações Importantes

- ⚠️ A troca entre os ambientes (`dev` e `prod`) depende exclusivamente do header `X-ENV`. Se não for passado, será usado **dev**.
- 📜 A estrutura dos JSONs está detalhada no Swagger, na seção **Schemas** no final da documentação.
- 🔗 Se houver mudança de ambiente (link, deploy ou banco), lembre-se de atualizar o `openapi-configuration.yaml` para refletir corretamente o novo servidor.

---
