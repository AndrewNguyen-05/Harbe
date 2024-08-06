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
  <img src="https://drive.usercontent.google.com/download?id=1wA261jIeTtkyrxBmq26IfKA-y42YSGLA", alt="Application Architecture" />
  <p><i>Application Architecture</i></p>
</div>

- I implement a basic circuit breaker flow for demo.

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1jczjwhfzF86IVopPnKKtFNHoiKWgM5uB", alt="Circuit Breaker flow" width="600"/>
  <p><i>Circuit Breaker</i></p>
</div>

- This is the general Usecase Diagram for this application

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1c66zzqJHpGgXi8WZJXbR5nL56UcPXQaw", alt="General Usecase Diagram" />
  <p><i>General Usecase Diagram</i></p>
</div>

- My application also implements Grafana and Prometheus to monitor, this is the general flow of Grafana and Prometheus:

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1PAZ6Jo9lL-OIe7ZrrpfJJpHs0BvoV_rI", alt="Monitoring flow" width="600"/>
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
  <img src="https://drive.usercontent.google.com/download?id=1wxsy6LTbUrVLR8fEzHXqMldh12AyBOV-", alt="Home page" width="600" />
  <p><i>Homepage Screen</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1EPww4kKBp0hoBXJY6THnOA944H3dRoTh", alt="Detail product" width="600" />
  <p><i>Detail product Screen</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1u87z1HMVw_gqhbb_mLWOll6SAKYg_4YN", alt="Admin product" width="600" />
  <p><i>Admin product Screen</i></p>
</div>

- Back-end:

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1-Yn0k2-nODih3557Kme6FOlhlGUINf1P", alt="Spring Eureka" width="600" />
  <p><i>Spring Netflix Eureka</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1JRFvBNhERrNju3VqS15R6k_Yjvbp_S3j", alt="Prometheus" width="600" />
  <p><i>Prometheus Screen</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=11F_o95TywiyR5foJSgoOAsYMbANX1KA6", alt="Grafana" width="600" />
  <p><i>Grafana Screen</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1eXAJ8xqbwG4OSnzmBF8g8dipOP8oLVQf", alt="Zipkin General" width="600" />
  <p><i>Zipkin Screen</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1IXhUaYcJbGl4Hv7xWGEQNSFJY3B9K0iA", alt="Zipkin" width="600" />
  <p><i>Zipkin Detail Screen</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1qMTwSLxdDXQgwSGqJI82Dq1gHGRREv61", alt="Zipkin Graph" width="600" />
  <p><i>Zipkin Graph Screen</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1g1yg0K08o85LVbkpJG1l2_7itZb2RqsK", alt="ELK Stack" width="600" />
  <p><i>ELK Stack Screen</i></p>
</div>

<div align="center">
  <img src="https://drive.usercontent.google.com/download?id=1WVUFI6WA9eBQL8X5G8iYOd6s5NUa_V_Z", alt="ELK Stack" width="600" />
  <p><i>ELK Stack Screen</i></p>
</div>
