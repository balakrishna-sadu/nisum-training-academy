package com.nisum.temporary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CronEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(CronEmailApplication.class, args);
	}
}
