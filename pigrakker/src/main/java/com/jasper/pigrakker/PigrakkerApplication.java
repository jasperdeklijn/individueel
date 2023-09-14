package com.Jasper.pigrakker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableJpaRepositories("com.Jasper.pigrakker.repository")
@EntityScan("com.Jasper.pigrakker.model")
@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class PigrakkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigrakkerApplication.class, args);
	}

}
