
# Stage 1: Build the Spring Boot application
FROM openjdk:20-jdk
COPY . .
RUN mvn clean package -DskipTests
COPY  /target/pigrakker-0.0.1-SNAPSHOT.jar pigrakker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","pigrakker.jar"]