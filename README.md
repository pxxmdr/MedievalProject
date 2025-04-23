# Medieval Project

Bem-vindo ao **Market Skyrim**, um sistema para gerenciamento de personagens e classes no universo medieval.

## Descrição
Este projeto utiliza **Spring Boot** e **JPA** para gerenciar entidades como `Personagem` e `Classe`. Ele permite criar, listar e buscar personagens com base em atributos como nome e classe.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Lombok**
- **Jakarta Validation**
- **Swagger** (para documentação da API)

## Configuração do Swagger
A documentação da API está disponível via Swagger. Para acessá-la, inicie o projeto e navegue até: GitHub Copilot
Aqui está um exemplo de um arquivo README.md para o seu projeto:

http://localhost:8080/swagger-ui/index.html


### Desenvolvedores
- **Seu Nome**: [Adicione seu nome aqui]
- **Nome do Amigo**: [Adicione o nome do seu amigo aqui]

## Funcionalidades
- **Gerenciamento de Personagens**:
  - Criação de personagens com atributos como `nome`, `nível`, `moeda` e `classe`.
  - Busca de personagens por nome (case insensitive).
  - Busca de personagens por classe.

- **Gerenciamento de Classes**:
  - Enumeração de classes disponíveis: `Guerreiro`, `Mago`, `Arqueiro`.

## Estrutura do Projeto
- **Modelos**: Contém as entidades do sistema (`Personagem`, `Classe`).
- **Repositórios**: Interfaces para acesso ao banco de dados (`PersonagemRepository`).
- **Configurações**: Configuração do Swagger e outras dependências.
