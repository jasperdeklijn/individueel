
FROM maven:3.9.4 AS build
COPY . .
RUN mvn clean package -DskipTests
# Stage 1: Build the Spring Boot application
FROM openjdk:20-jdk
WORKDIR /app
COPY target/pigrakker-0.0.1-SNAPSHOT.jar /app/springdemo.jar
EXPOSE 8080
CMD ["java", "-jar", "springdemo.jar"]