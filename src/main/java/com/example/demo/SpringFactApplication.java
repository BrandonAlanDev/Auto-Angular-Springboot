package com.example.demo;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringFactApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringFactApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		if (Files.notExists(Paths.get("uploads"))){
			Files.createDirectories(Paths.get("uploads"));
		}
		
	}
}
