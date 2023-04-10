# Technical Challenge 

## Requirements
- Java 17
- Docker

## Decisions

### JOB's

[SessionResultJob](https://github.com/victorgmacedo/ntconsult/blob/main/src/main/kotlin/br/com/ntconsult/challenge/job/SessionResultJob.kt)

Para simplificar a estrutura do projeto resolvi usar um Job para gerar o resultado das assembleias, então a cada minuto esse job vai consultar no BD as sessões que já terminaram e que estão sem resultado consolidado, em seguida, este Job processará os votos, salvará no banco de dados o resultado e enviará uma mensagem para uma fila no rabbitmq ``session.result``.

Pensando também na escalabilidade do projeto defini um limite de 100 registros para cada consulta as sessões finalizadas dessa forma o processo pode ser dividido entre varios serviços de acordo com a necessidade
e para garantir que os mesmos registros não serão processados em mais de um seriviço utilizei a estrategia de lock, ou seja os registros que estão sendo processados em uma instancia, 
não poderam ser acessados em outra.


## Usage

### Run

``docker-compose up -d``

``./gradlew build``

``./gradlew bootRun``

### Swagger

http://localhost:8080/swagger-ui.html

### RabbitMQ

Para verificar os resultados das assembleias é necessario acessar a interface do RabbitMQ que subiu no docker-compose

 http://localhost:15672/
 
 **User**: guest
 
 **Password**: guest

## Performance Test

O teste de performance foi feito utilizando o locust, e os resultados podem ser verificados abaixo.

O Arquivo utilizado para execução do teste pode ser encontrado no seguinte caminho [VoteTestPerformance](locust/vote-performance.py)

### RPS (Request per second)
- AVG = 130

### Response Time
- 95% = 350ms
- 50% = 230ms

![total_requests_per_second_1681129698](https://user-images.githubusercontent.com/22604745/230904614-4fd2a69c-aad1-40e4-a109-eb7eb1058c96.png)
