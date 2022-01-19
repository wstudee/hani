package com.runHani.demo;

import javax.persistence.Entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.runHani")
public class RunHaniApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunHaniApplication.class, args);
	}

}
