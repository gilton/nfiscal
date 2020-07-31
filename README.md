# Fiscal

Em nossa solução RPA (Robotic Process Automation), por suas características de
produto necessita de uma estrutura descentralizada, criada a partir de máquinas com funcionalidades distintas que se comunicam via API (Application Programming Interface).
Esse teste tem a intenção de fazer você pensar um pouco fora da caixa e
implementar uma API simples de gerenciamento de notas fiscais.


## Construído com

* 	[Maven](https://maven.apache.org/) - Gerenciador de Dependências
* 	[JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit - Versão 8
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework utilizado facilitar e agilizar as Aplicações Springs
* 	[H2](http://www.h2database.com/html/download.html) - Banco de Dados Relacional, **está incluso no JDK**
* 	[Lombok](https://projectlombok.org/) - Projeto usado para agilizar o desenvolvimento tirando a necessidade de escrever getters, setters ou equals


## Ferramenta Externa Usada

* [Postman](https://www.getpostman.com/) - Ambiente de Desenvolvimento de Testes e Documentação
* [Astah Community](https://astah.net/products/astah-community/) - Ambiente de Desenvolmento de Diagramas UML

## Abordagens Tomadas

- [x] REST API (CRUD)
- [x] JUnit (Test)
- [x] UML



## Executando o projeto localmente

- Faça o download the zip or clone o Git repositório
- Descompacte o arquivo zip (caso você tenha realizado o download)
- Abra o prompt de comando ou o Terminal/Shell e mude o diretório através do comando (cd) para a pasta que contenha o arquivo **pom.xml**
- Abra o Eclipse / STS / IntelliJ / outros
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you descompacte o arquivo zip
   - Selecione o projeto
- Por fim para executar: 
	- Selecione o Spring Boot Application em *Run as* 

Alternativamente se pode usar o  [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) no terminal como a seguir:

```shell
mvn spring-boot:run
```

### URLs

	Empresa

|  URL |  Method | 
|----------|--------------|
|`http://localhost:8080/api/v1/empresa/list`	|GET
|`http://localhost:8080/api/v1/empresa/add`	|POST
|`http://localhost:8080/api/v1/empresa/update/{id}`	|PUT                       
|`http://localhost:8080/api/v1/empresa/delete/{id}` | DELETE |
|`http://localhost:8080/api/v1/empresa/find/{id}`   | GET |

##
	Nota Fiscal

|  URL |  Method | 
|----------|--------------|
|`http://localhost:8080/api/v1/notafiscal/list`	|GET
|`http://localhost:8080/api/v1/notafiscal/add`	|POST                      
|`http://localhost:8080/api/v1/notafiscal/find/{id}`   | GET |
|`http://localhost:8080/api/v1/notafiscal/pesquisaPorEmpresa/{pesquisa}`   | GET |


## Documentação

* <u>Postman Collection scripts</u>:  formato TXT no local: {project}/postman/
* <u>Diagrama de Classe</u>: formato PNG no local: {project}/diagram/ [versão 01]




