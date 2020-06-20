package com.daria.amqp.kata.tutorials.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile({"tutl1", "hello-world"})
@Configuration
public class Tut1Config {
    @Bean
    public Queue hello() {
        System.out.println("큐 맹들어!!");
        return new Queue("hello");
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        System.out.println("receiver 띄워!");
        return new Tut1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}
