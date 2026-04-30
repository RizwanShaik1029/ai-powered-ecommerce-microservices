package com.srb.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
class NotificationApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplicationTests.class, args);
	}


}
