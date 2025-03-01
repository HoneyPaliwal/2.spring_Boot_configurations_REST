🚀 Spring Boot Project 2: Exploring Advanced Configurations! 🚀

This project dives deep into some essential Spring Boot concepts and configurations — here’s a quick overview of what I’ve implemented:

📚 Topics Covered

1️⃣ Spring Boot Actuator

Dependency:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <version>3.4.2</version>
</dependency>

Automatically exposes endpoints to monitor and manage your application.

DevOps functionality out-of-the-box.

Configuration in application.properties:

management.endpoints.web.exposure.include=health,info
management.info.env.enabled=true

Access endpoints:

http://localhost:8080/actuator/health
http://localhost:8080/actuator/info

Enable all endpoints:

management.endpoints.web.exposure.include=*
management.info.env.enabled=true

Secure endpoints by excluding specific ones:

management.endpoints.web.exposure.exclude=health,info

2️⃣ Spring Boot Actuator Security

Dependency:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>3.4.2</version>
</dependency>

Default user credentials: user with auto-generated console password.

Custom credentials in application.properties:

spring.security.user.name=honey
spring.security.user.password=honey123

Access secure endpoint with credentials:

http://localhost:8080/actuator/info

3️⃣ CLI Application Setup

Environment Setup:

JAVA_HOME=C:\Program Files\Java\jdk-21
Path=%JAVA_HOME%\bin

Navigate to project folder:

cd C:\path\to\project

Package app:

mvnw package

Run the jar:

java -jar configurations-0.0.1-SNAPSHOT.jar

Stop app:

CTRL+C

4️⃣ Custom Application Properties

Define variables in application.properties:

teacher.name=Huang Sun
teacher.qualification=Senior Software Developer [Spring Boot]
teacher.location=China

Inject properties in controller:

@Value("${teacher.name}")

Access endpoint:

http://localhost:8080/configurations/teacher

5️⃣ Advanced Spring Boot Configurations

Logging:

logging.file.path=C:/logs
logging.file.name=C:/logs/configurations.log

Server Config:

server.port=7070
server.servlet.session.timeout=15m

6️⃣ Student Management API

Created a POJO class Student.

API endpoint:

http://localhost:8080/configurations/students

7️⃣ Path Variables & Exception Handling

Fetch specific student data:

http://localhost:8080/students/{studentId}

Custom exception handling for invalid input.

8️⃣ Global Exception Handling

Handles unexpected and invalid input gracefully.

9️⃣ CRON Job Implementation

Scheduled log cleaner job.

@Scheduled(cron = "*/30 * * * * *")


#SpringBoot #Java #BackendDevelopment #Microservices #Tech #Programming #SoftwareEngineering #SpringSecurity #Actuator #Cron #ExceptionHandling #Coding

