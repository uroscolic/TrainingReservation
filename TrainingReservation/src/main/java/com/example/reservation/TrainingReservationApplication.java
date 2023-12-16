package com.example.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TrainingReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingReservationApplication.class, args);
	}

}
