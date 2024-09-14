package com.example.real_chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RealChatApplication {

	public static void main(String[] args) {

		System.out.println("hello");
		SpringApplication.run(RealChatApplication.class, args);
	}

}
