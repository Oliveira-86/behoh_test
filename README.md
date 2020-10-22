# behoh_test

Projeto API REST com Spring-Boot.
``
### Executar localmente
Para excutar o projeto bastar usar a classe de aplicação:
`@SpringBootApplication`

### TomCat
O Tomcat é um servidor web, mais especificamente, um container de servlet. É inicializado na portaria: `8080 (http).`

### URL
Um endpoint de um web service é a URL onde seu serviço pode ser acessado pelo cliente. Exemplo:

`{GET} http://localhost:8080/events/1`

### Banco de Dados
Utilizamos inicialmente o BD H2 em memória, ótimo para testes. Configurado no arquivo:

`application-test.properties`

Executando no arquivo:

`application.properties`

`spring.profiles.active=test`

Em seguida foi ultilizado o BD MySQL para desenvolvimento, no arquivo:

`application-dev.properties`

Executando no arquivo:

`application.properties`

`spring.profiles.active=dev`

Injetando suas dependências. Utilizando todas as  Annotations necessária do Spring-Data-JPA como mapeamento objeto relacional.

### Tratamento de exceções
Criamos classes para gerar nossas exceções personalizadas, usando a camada de serviço para trata-las com as melhores práticas do mercado.

`@ControllerAdvice`

