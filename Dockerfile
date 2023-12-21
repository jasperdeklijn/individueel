FROM mariadb:latest

ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV MYSQL_DATABASE=pigrakker
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=admin

# Stage 1: Build the Spring Boot application
FROM openjdk:20-jdk
WORKDIR /app



COPY target/pigrakker-0.0.1-SNAPSHOT.jar /app/springdemo.jar
EXPOSE 8080
CMD ["java", "-jar", "springdemo.jar"]
