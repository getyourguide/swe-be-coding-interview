package com.getourguide.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GetYourGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetYourGuideApplication.class, args);
	}

}
