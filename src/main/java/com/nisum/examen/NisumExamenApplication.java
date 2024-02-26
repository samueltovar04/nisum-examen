package com.nisum.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.nisum.examen.repository")
@PropertySource("classpath:application.properties")
public class NisumExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(NisumExamenApplication.class, args);
	}

}
