package com.daria.amqp.kata.tutorials.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


/**
 * A work queue with two consumers.
 * https://www.rabbitmq.com/tutorials/tutorial-two-spring-amqp.html
 */
@Profile({"tut2", "work-queues"})
@Configuration
public class Tut2Config {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Profile("receiver")
    public static class ReceiverConfig {
        @Bean
        public Tut2Receiver receiver1() {
            System.out.println("receiver1 등록");
            return new Tut2Receiver(1);
        }

        @Bean
        public Tut2Receiver receiver2() {
            System.out.println("receiver2 등록");
            return new Tut2Receiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public Tut2Sender sender() {
        System.out.println("sender 등록");
        return new Tut2Sender();
    }
}
