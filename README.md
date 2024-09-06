![UIT](https://img.shields.io/badge/from-UIT%20VNUHCM-blue?style=for-the-badge&link=https%3A%2F%2Fwww.uit.edu.vn%2F)

Link to Frontend repo: [Harbe FE](https://github.com/AndrewNguyen-05/Harbe-FE)

# Harbe

### Contributors

- Nguyen Van Hoang Anh - 21520144@gm.uit.edu.vn - [Github](https://github.com/AndrewNguyen-05) - [Facebook](https://www.facebook.com/andrew.nguyen0505/)
- Hoang Duc Manh - 21520062@gm.uit.edu.vn - [Github](https://github.com/Marowota) - [Facebook](https://www.facebook.com/hoangducmanh254)

### Supervisors

- Nguyen Trinh Dong - dongnt@uit.edu.vn

### Description

- Harbe is an ecommerce system utilizing microservices architecture, using NextJS, ReactJS, TailwindCSS for frontend.
- I use SpringBoot to build services, Spring Cloud to build gateway, config server for microservices, Resilience4j to handle error, RabbitMQ to sync between services and Zipkin for tracing.
- The database I choose for this project is MySQL and Redis for cart service.
- You can see the report [here](https://drive.google.com/file/d/1MxEw0X9wGU1nWqVVXuw1AJ_YOFRNLfJf/view?usp=sharing)

<div align="center">
  <img src="app-resources/Picture17.png", alt="Application Architecture" />
  <p><i>Application Architecture</i></p>
</div>

- I implement a basic circuit breaker flow for demo.

<div align="center">
  <img src="app-resources/Picture19.png", alt="Circuit Breaker flow" width="600"/>
  <p><i>Circuit Breaker</i></p>
</div>

- This is the general Usecase Diagram for this application

<div align="center">
  <img src="app-resources/Picture16.png", alt="General Usecase Diagram" />
  <p><i>General Usecase Diagram</i></p>
</div>

- My application also implements Grafana and Prometheus to monitor, this is the general flow of Grafana and Prometheus:

<div align="center">
  <img src="app-resources/Picture18.png", alt="Monitoring flow" width="600"/>
  <p><i>Monitoring Flow</i></p>
</div>


### Technologies and Framework
- Back-end
  - Java 17
  - Spring Boot 3: Authorization Server (OAuth 2), Statemachine ...
  - Spring Cloud Gateway, Open Feign, Stream ...
  - Elastic stack: Elasticsearch, Logstash, Kibana, Filebeat
  - Grafana stack: Prometheus, Grafana
  - Zipkin
  - Redis
- Front-end
  - NextJS 14
  - ReactJS
  - TailwindCSS
  - RadixUI
  - ShadcnUI

### Demo
Some of the pictures of this Application
- Front-end:

  
<div align="center">
  <img src="app-resources/Picture3.png", alt="Home page" width="600" />
  <p><i>Homepage Screen</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture2.png", alt="Detail product" width="600" />
  <p><i>Detail product Screen</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture4.png", alt="Admin product" width="600" />
  <p><i>Admin product Screen</i></p>
</div>

- Back-end:

<div align="center">
  <img src="app-resources/Picture14.png", alt="Spring Eureka" width="600" />
  <p><i>Spring Netflix Eureka</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture9.png", alt="Prometheus" width="600" />
  <p><i>Prometheus Screen</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture10.png6", alt="Grafana" width="600" />
  <p><i>Grafana Screen</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture12.png", alt="Zipkin General" width="600" />
  <p><i>Zipkin Screen</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture11.png", alt="Zipkin" width="600" />
  <p><i>Zipkin Detail Screen</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture13.png", alt="Zipkin Graph" width="600" />
  <p><i>Zipkin Graph Screen</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture7.png", alt="ELK Stack" width="600" />
  <p><i>ELK Stack Screen</i></p>
</div>

<div align="center">
  <img src="app-resources/Picture8.png", alt="ELK Stack" width="600" />
  <p><i>ELK Stack Screen</i></p>
</div>
