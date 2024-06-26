package com.devsuperior.bds02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.devsuperior.bds02")
public class Bds02Application {

	public static void main(String[] args) {
		SpringApplication.run(Bds02Application.class, args);
	}

}
