package com.daria.amqp.kata.tutorials.tut3;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * The fanout pattern to deliver a message to multiple consumers.
 * Essentially, published messages are going to be broadcast to all the receivers.
 * https://www.rabbitmq.com/tutorials/tutorial-three-spring-amqp.html
 */
@Profile({"tut3", "pub-sub", "publish-subscribe"})
@Configuration
public class Tut3Config {

    @Bean
    public FanoutExchange fanout() {
        System.out.println("fanout bean 등록");
        return new FanoutExchange("tut.fanout");
    }

    @Profile("sender")
    @Bean
    public Tut3Sender sender() {
        System.out.println("sender bean 등록");
        return new Tut3Sender();
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            System.out.println("autoDeleteQueue1 bean 등록");
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            System.out.println("autoDeleteQueue2 bean 등록");
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
            System.out.println("autoDeleteQueue1 binding1");
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
            System.out.println("autoDeleteQueue2 binding2");
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public Tut3Receiver receiver() {
            System.out.println("receiver bean 등록");
            return new Tut3Receiver();
        }
    }
}
