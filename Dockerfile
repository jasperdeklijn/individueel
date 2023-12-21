# Stage 1: Build the Spring Boot application
FROM openjdk:20-jdk AS builder

WORKDIR /app

COPY target/pigrakker-0.0.1-SNAPSHOT.jar /app/springdemo.jar

# Stage 2: Create the final image with MariaDB
FROM mariadb:latest

ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV MYSQL_DATABASE=pigrakker
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=admin

COPY --from=builder /app/springdemo.jar /app/springdemo.jar

# Expose the port for the Spring Boot application
EXPOSE 8080

# Copy wait-for-it script
COPY wait-for-it.sh /usr/wait-for-it.sh
RUN chmod +x /usr/wait-for-it.sh

# Start MariaDB and then run the Spring Boot application
CMD ["bash", "-c", "/usr/wait-for-it.sh db:3306 -- /usr/bin/mysqld_safe --skip-grant-tables & java -jar /app/springdemo.jar"]
