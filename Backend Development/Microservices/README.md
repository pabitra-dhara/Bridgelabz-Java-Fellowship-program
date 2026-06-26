Fundoo Notes Microservices:



Fundoo Notes Application built using Spring Boot Microservices. This project demonstrates user authentication with JWT, service discovery using Eureka, API Gateway, asynchronous communication using RabbitMQ, email notifications, and MySQL database integration.



Tech Stack:



\* Java 19

\* Spring Security

\* JWT Authentication

\* Spring Cloud Gateway

\* Spring Cloud Eureka

\* Spring Data JPA

\* Hibernate

\* MySQL

\* RabbitMQ

\* Java Mail Sender

\* Maven



Microservices:



Eureka Server        | 8761 | Service Registry          

API Gateway          | 8080 | Single Entry Point        

User Service         | 8081 | User Registration \& Login 

Note Service         | 8082 | CRUD Operations for Notes 

Attachment Service   | 8083 | File Upload \& Download    

Notification Service | 8084 | Email Notification        

Reminder Service     | 8085 | Reminder Management       



Features:



1.User Service



\* User Registration

\* User Login

\* BCrypt Password Encryption

\* JWT Token Generation

\* JWT Authentication

\* User Profile Management



2.Note Service



\* Create Note

\* Update Note

\* Delete Note

\* Archive Note

\* Trash Note

\* Restore Note

\* Search Notes



3.Reminder Service



\* Create Reminder

\* Update Reminder

\* Delete Reminder

\* View All Reminders

\* Publish Reminder Event to RabbitMQ



4.Notification Service



\* Consume RabbitMQ Messages

\* Send Reminder Email

\* Gmail SMTP Integration



5.Attachment Service



\* Upload File

\* Download File

\* Delete Attachment



RabbitMQ Flow:

Reminder Service -> RabbitMQ Queue -> Notification Service -> Email Sent



Start Services:



Run in this order:

1\. Eureka Server

2\. API Gateway

3\. User Service

4\. Note Service

5\. Attachment Service

6\. Reminder Service

7\. Notification Service



Start MySQL:

MySQL Server 3306

Start RabbitMQ:

RabbitMQ Server

RabbitMQ Dashboard: http://localhost:15672

Username: guest

Password: guest

Email Configuration:

spring.mail.host=smtp.gmail.com

spring.mail.port=587

spring.mail.username=pabitradharardh@gmail.com

spring.mail.password=xgkmjymlmrvqzdee



spring.mail.properties.mail.smtp.auth=true

spring.mail.properties.mail.smtp.starttls.enable=true





