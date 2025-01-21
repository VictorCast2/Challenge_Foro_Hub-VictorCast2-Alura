package com.Foro_Hub.Alura.Foro_Hub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class ForoHubApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ForoHubApplication.class, args);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {

	}

}