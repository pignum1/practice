package com.cloud.rabbit.sample;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author WXY
 * @ClassName Receiver2
 * @Description T0D0
 * @Date 2019/9/2 22:20
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "hello")
public class Receiver2 {
    @RabbitHandler
    public void process(String hello) throws InterruptedException {
        System.out.println("Receiver2 : " + hello);
        Thread.sleep( 3000 );
    }
}