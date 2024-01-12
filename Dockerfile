
# Stage 1: Build the Spring Boot application
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests \
FROM openjdk:20-jdk
COPY --from=build /target/pigrakker-0.0.1-SNAPSHOT.jar pigrakker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","pigrakker.jar"]