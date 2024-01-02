# InduvidueelProject



## Project Structure

```
project-root
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.demo
│   │   │       ├── config             # Configurations
│   │   │       ├── controller         # REST controllers
│   │   │       ├── model              # Entity classes and other domain-specific classes
│   │   │       ├── repository         # Spring Data repositories
│   │   │       ├── service            # Service classes
│   │   │       └── PigrakkerApplication.java   # Main Spring Boot application class
│   │   │
│   │   ├── resources
│   │   │   ├── application.properties # Application configuration
│   │   │   └── static
│   │   │       ├── css                # Static resources (e.g., stylesheets)
│   │   │       └──images             # Statig images
│   │
│   └── test
│       ├── java
│       │   └── com.example.demo
│       │       ├── controller         # Test classes for controllers
│       │       ├── service            # Test classes for services
│       │       └── ApplicationTests.java # Test class for the main application
│       │
│       └── resources
│           └── application-test.properties # Test-specific application configuration
│
├── target                             # Compiled bytecode and packaged JAR
├── .gitignore                         # Git ignore file
├── mvnw                               # Maven wrapper script (for Unix-based systems)
├── mvnw.cmd                           # Maven wrapper script (for Windows)
├── pom.xml                            # Maven Project Object Model file
└── README.md                          # Project documentation (you are here)
```
docker run -p 9090:8080 --name pigrakkerspring --net pigrakker-network -e MYSQL_HOST=pigrakker -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -e MYSQL_PORT=3306 jasperdeklijn/individueel:today
docker run -p 3307:3306 --name pigrakkerdb -e MYSQL_ROOT_PASSWORD=myrootpassword -e MYSQL_DATABASE=pigrakker mariadb