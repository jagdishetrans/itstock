package com.etrans.itstock;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		System.out.println(new Date().toString());
		SpringApplication.run(MainApplication.class, args);
	}

}
