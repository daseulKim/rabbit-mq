package com.daria.amqp.kata.tutorials.tut1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StopWatch;

public class RabbitAmqpTutorialRunner implements CommandLineRunner {
    @Value("${tutorial.client.duration:0}")
    private int duration;

//    @Value("${spring.rabbitmq.listener.simple.prefetch}")
//    private int prefetch;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Ready ...... running for " + duration + "ms");
//        System.out.println("prefetch : " + prefetch);
        Thread.sleep(duration);
        stopWatch.stop();
        System.out.println(" running time : " + stopWatch.getTotalTimeMillis() + "ms");
        context.close();
    }
}
