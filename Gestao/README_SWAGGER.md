# Swagger UI - API de Gestão Bancária

## Visão Geral

Este projeto foi configurado com Swagger UI para documentação da API, sendo totalmente compatível com **Tomcat 10.1** e **Jakarta EE**.

## Configuração Implementada

### Dependências Adicionadas
- `swagger-core` (v2.2.20) - Biblioteca principal do Swagger
- `swagger-jaxrs2` (v2.2.20) - Integração com JAX-RS
- `swagger-annotations` (v2.2.20) - Anotações para documentação
- `jackson-databind` e `jackson-jaxrs-json-provider` - Para processamento JSON

### Arquivos Modificados/Criados

1. **pom.xml** - Adicionadas dependências do Swagger
2. **RestApplication.java** - Configurado OpenAPI e Swagger
3. **ContaResource.java** - Adicionadas anotações Swagger
4. **TransacaoResource.java** - Adicionadas anotações Swagger
5. **ContaDTO.java** - Adicionadas anotações de schema
6. **TransacaoDTO.java** - Adicionadas anotações de schema
7. **ResponseDTO.java** - Adicionadas anotações de schema
8. **swagger-ui/index.html** - Interface do Swagger UI

## Como Acessar

### 1. Especificação OpenAPI (JSON)
```
http://localhost:8080/Gestao/api/openapi.json
```

### 2. Interface Swagger UI
```
http://localhost:8080/Gestao/swagger-ui/index.html
```

## Endpoints Documentados

### Conta
- **POST** `/api/conta` - Criar nova conta
- **GET** `/api/conta?numero_conta={numero}` - Consultar conta

### Transação
- **POST** `/api/transacao` - Criar nova transação

## Exemplos de Uso

### Criar Conta
```json
{
  "numeroConta": 123456789,
  "saldo": 1000.00
}
```

### Consultar Conta
```
GET /api/conta?numero_conta=123456789
```

### Criar Transação
```json
{
  "formaPagamento": "D",
  "numeroConta": 123456789,
  "valor": 100.50
}
```

## Formas de Pagamento
- **D** - Débito
- **C** - Crédito
- **P** - PIX

## Compatibilidade

✅ **Tomcat 10.1** - Totalmente compatível
✅ **Jakarta EE** - Usa apenas pacotes `jakarta.*`
✅ **Java 17** - Compatível
✅ **JAX-RS (Jersey 3.1.0)** - Integração nativa

## Recursos do Swagger UI

- **Try it out** - Teste os endpoints diretamente na interface
- **Documentação automática** - Baseada nas anotações Java
- **Exemplos de requisição/resposta** - Gerados automaticamente
- **Validação de esquemas** - Validação JSON automática
- **Download da especificação** - OpenAPI JSON/YAML

## Troubleshooting

### Problema: Swagger UI não carrega
**Solução:** Verifique se o Tomcat está rodando e acesse `http://localhost:8080/Gestao/swagger-ui/index.html`

### Problema: Especificação OpenAPI não encontrada
**Solução:** Verifique se a aplicação foi deployada corretamente e acesse `http://localhost:8080/Gestao/api/openapi.json`

### Problema: Erros de CORS
**Solução:** O Swagger UI está configurado para acessar a API no mesmo domínio, não deve haver problemas de CORS.

## Próximos Passos

1. **Deploy da aplicação** no Tomcat 10.1
2. **Acesso ao Swagger UI** via navegador
3. **Teste dos endpoints** usando a interface
4. **Personalização** da documentação conforme necessário 