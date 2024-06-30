![UIT](https://img.shields.io/badge/from-UIT%20VNUHCM-blue?style=for-the-badge&link=https%3A%2F%2Fwww.uit.edu.vn%2F)
Link to Frontend repo: [Harbe FE](https://github.com/AndrewNguyen-05/Harbe-FE)

# Harbe

### Contributors

- Nguyen Van Hoang Anh - 21520144@gm.uit.edu.vn - [Github](https://github.com/AndrewNguyen-05)
- Hoang Duc Manh - 21520062@gm.uit.edu.vn - [Github](https://github.com/Marowota)

### Supervisors

- Nguyen Trinh Dong - dongnt@uit.edu.vn

### Description

Harbe is an ecommerce system utilizing microservices architecture, using NextJS, ReactJS, TailwindCSS for frontend. I use SpringBoot to for services, Spring Cloud to make gateway, config server for microservices, Resilience4j to handle error, RabbitMQ to sync between services and Zipkin for tracing. The database I choose for this project is MySQL and Redis for cart service.
You can see the report [here](https://drive.google.com/file/d/1MxEw0X9wGU1nWqVVXuw1AJ_YOFRNLfJf/view?usp=sharing)

![Alt Text](https://drive.google.com/file/d/1wA261jIeTtkyrxBmq26IfKA-y42YSGLA/view?usp=sharing)

I implement a basic circuit breaker flow for demo.

![Alt Text](https://drive.google.com/file/d/1jczjwhfzF86IVopPnKKtFNHoiKWgM5uB/view?usp=sharing)

This is the general Usecase Diagram for this application

![Alt Text](https://drive.google.com/file/d/1c66zzqJHpGgXi8WZJXbR5nL56UcPXQaw/view?usp=sharing)

My application also implements Grafana and Prometheus to monitor, this is the general flow of Grafana and Prometheus:

![Alt Text](https://drive.google.com/file/d/1PAZ6Jo9lL-OIe7ZrrpfJJpHs0BvoV_rI/view?usp=sharing)


### Technologies and Framework
- Java 17
- Spring Boot 3: Authorization Server (OAuth 2), Statemachine ...
- Spring Cloud Gateway, Open Feign, Stream ...
- Elastic stack: Elasticsearch, Logstash, Kibana, Filebeat
- Grafana stack: Prometheus, Grafana
- Zipkin
- Redis
