# behoh_test

Projeto API REST com spring-boot.
``
### Executar localmente
Para excutar a aplicação bastar usara classe de aplicação:
`@SpringBootApplication`

### TomCat
O Tomcat é um servidor web, mais especificamente, um container de servlet. é inicializado na portaria: `8080 (http).`

### URL
Um endpoint de um web service é a URL onde seu serviço pode ser acessado por uma aplicação cliente. Exemplo:

`{GET} http://localhost:8080/events/1`

### Banco de Dados
Utilizamos inicialmente o BD H2 em memória, ótimo para testes. Configurando no arquivo:

`application-test.properties`

Executando no arquivo:

`application.properties`

`spring.profiles.active=test`

Em seguida foi ultilizado o BD MySQL para desenvolvimento no arquivo:

`application-dev.properties`

Executando no arquivo:

`application.properties`

`spring.profiles.active=dev`

Injetando suas depenências. Utilizando todas as  Annotations do Spring-JPA como mapeamento.

### Tratamento de exceções
Criações de classes e usando a camada de serviço para tratar as exceções, ultilizando as melhores práticas do mercado.

