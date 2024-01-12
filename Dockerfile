
# Stage 1: Build the Spring Boot application
FROM maven:3.9.4
RUN mvn clean package
FROM openjdk:20-jdk

WORKDIR /app
COPY /target/pigrakker-0.0.1-SNAPSHOT.jar pigrakker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","pigrakker.jar"]