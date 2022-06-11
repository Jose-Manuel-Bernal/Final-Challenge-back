package com.sofkau.finallChallenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "FinalChallenge", version = "1.0", description = "Backend"))
public class FinallChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinallChallengeApplication.class, args);
	}

}
