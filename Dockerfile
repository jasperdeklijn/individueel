
FROM maven:3.9.4 AS build
COPY . .
RUN mvn clean package -DskipTests --file pom.xml

FROM openjdk:20-jdk
WORKDIR /app
COPY --from=build target/pigrakker-0.0.1-SNAPSHOT.jar /app/springdemo.jar
EXPOSE 8080
CMD ["java", "-jar", "springdemo.jar"]