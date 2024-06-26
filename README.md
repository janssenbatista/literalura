# LiterAlura

Projeto desenvolvido para conclusão de um dos desafios da formação back-end Java e Spring-Framework T6-ONE da empresa [Alura](https://www.alura.com.br/) em parceria com a [Oracle Next Education](https://www.oracle.com/br/education/oracle-next-education/).

### Endpoints

|                  Método                  | Endpoint                    |                                                                                                                    |
|:----------------------------------------:|:----------------------------|--------------------------------------------------------------------------------------------------------------------|
| <span style="color: #1F78D1;">GET</span> | /books                      | retorna todos os livros que estão salvos no banco de dados local                                                   |
| <span style="color: #1F78D1;">GET</span> | /books/{title}              | busca livros por um determinado título na API [Gutendex](https://gutendex.com/) e armazena no banco de dados local |
| <span style="color: #1F78D1;">GET</span> | /books/top10                | busca os 10 livros mais baixados e que estão salvos no banco de dados local                                        |
| <span style="color: #1F78D1;">GET</span> | /books/languages/{language} | busta todos os livros de um determinado idioma que estão salvos no banco de dados local                            |
| <span style="color: #1F78D1;">GET</span> | /authors                    | busca todos os autores salvos no banco de dados local                                                              |
| <span style="color: #1F78D1;">GET</span> | /authors/{aliveInYear}      | busca todos os autores que ainda estavam vivos em um determinado ano                                               |

### Setup
- Essa API utiliza o SGBD PostgreSQL para armazenar as informações vindas da API [Gutendex](https://gutendex.com/)
- Caso queira o utilizar o postgresql em container docker, na pasta raiz do projeto tem o arquivo docker-compose.yml. Basta rodar o seguinte comando para subir o banco de dados:
```bash
docker compose up -d 
```
⚠ Vale lembrar que é necessário ter o docker e docker compose instalados em sua máquina.
- Crie um arquivo application.properties na raiza do projeto e adiciona as seguintes informações:
```bash
spring.application.name=LiterAlura

spring.datasource.url=jdbc:postgresql://{URL DO BANCO DE DADOS}/literalura_db
spring.datasource.username={NOME DO USUÁRIO DO BD}
spring.datasource.password={SENHA DO BD}
spring.datasource.driver-class-name=org.postgresql.Driver 
```
