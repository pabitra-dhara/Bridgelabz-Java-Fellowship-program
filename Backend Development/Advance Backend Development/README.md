Fundoo Notes Backend:
A Spring Boot backend application inspired by Google Keep. The application provides secure note management with JWT authentication, reminders, labels, attachments, Redis integration, RabbitMQ asynchronous messaging, and email notifications.

Features:
1.User Management
User Registration
User Login
JWT Authentication
Forgot Password
Reset Password

2.Authentication & Authorization
Spring Security
JWT Token-Based Authentication

3.Notes Management
Create Notes
View Notes
Update Notes
Delete Notes

5.Notes Organization
Pin Notes
Archive Notes
Trash Notes
Restore Notes

6.Search & Filter
Search Notes by Title
Search Notes by Description
Filter Pinned Notes
Filter Archived Notes
Filter Trashed Notes

7.Labels Management
Create Labels
Update Labels
Delete Labels
Assign Labels to Notes
Remove Labels from Notes

8.Reminder Module
Add Reminder
Update Reminder
Delete Reminder
Get All Reminders

9.Email Notifications
SMTP Email Integration
Reminder Notification Emails

10.File Attachment Module
Upload Attachments
View Attachments
Delete Attachments

11.Redis Integration
Redis Connectivity
Token Caching Support

12.RabbitMQ Integration
Asynchronous Reminder Email Processing

13.Exception Handling
Global Exception Handling
Custom Exceptions

Technology Stack:
Spring Boot
Maven
Spring Security-JWT
JPA
ORM(Hibernate)
JMS
MySQL
Redis
RabbitMQ

Database Configuration:
spring.datasource.url=jdbc:mysql://localhost:3306/fundoo_notes
spring.datasource.username=root
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Redis Configuration:
spring.data.redis.host=localhost
spring.data.redis.port=6379

RabbitMQ Configuration:
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

Email Configuration:
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=pabitradharardha@gmail.com
spring.mail.password=dsfghjjkjkjkljhgh
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

JWT Configuration:
jwt.secret=mySuperSecretKeyForFundooNotesApplication
jwt.expiration=86400000

Running the Application:
Clone Repository:
git clone https:https://github.com/pabitra-dhara/Bridgelabz-Training-Java-Programming.git
Build Project:
mvn clean install
Run Application:
mvn spring-boot:run
Application starts at:
http://localhost:8080

Authentication Flow:
User Registration -> User Login -> JWT Token Generated -> Bearer Token Sent in Request Header->Access Protected API

RabbitMQ Reminder Flow:
Scheduler -> RabbitMQ Producer -> Reminder Queue -> RabbitMQ Consumer -> Email Service -> User Receives Reminder Email

