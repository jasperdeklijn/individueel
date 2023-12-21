FROM openjdk:20-jdk

WORKDIR /app

FROM mariadb:latest

# Set environment variables
ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV MYSQL_DATABASE=pigrakker
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=admin

# Optionally, expose the MySQL port (default is 3306)
EXPOSE 3306
COPY target/pigrakker-0.0.1-SNAPSHOT.jar /app/springdemo.jar
EXPOSE 8080


CMD ["java", "-jar", "springdemo.jar"]