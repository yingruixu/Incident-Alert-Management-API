package com.shaun.incident_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({ "com.shaun.incident_api", "com.shaun.monitor" })
@EnableScheduling
public class IncidentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentApiApplication.class, args);
	}

}
