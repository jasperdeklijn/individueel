package com.jasper.pigrakker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.jasper.pigrakker.repository")
@EntityScan("com.jasper.pigrakker.model")
@SpringBootApplication
public class PigrakkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PigrakkerApplication.class, args);
	}

}
