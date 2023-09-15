package com.jasper.pigrakker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EntityScan("com.Jasper.pigrakker.*")
@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class PigrakkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigrakkerApplication.class, args);
	}

}
