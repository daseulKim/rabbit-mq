package com.daria.amqp.kata.tutorials;

import com.daria.amqp.kata.tutorials.tut1.RabbitAmqpTutorialRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TutorialsApplication {

	@Profile("usage_message")
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring Profiles to control its behavior.\n");
			System.out.println("Sample usage: java -jar tutorial.jar --spring.profiles.active=hello-world,sender");
		};
	}


	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitAmqpTutorialRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(TutorialsApplication.class, args);
	}

}
