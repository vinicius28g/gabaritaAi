package com.trilhas.gabaritaAi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GabaritaAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GabaritaAiApplication.class, args);
		String encryptePassword = new BCryptPasswordEncoder().encode("1234");
		System.out.println(encryptePassword);
	}

}
