package com.cloud.rabbit.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author WXY
 * @ClassName Receiver2
 * @Description T0D0
 * @Date 2019/9/2 23:21
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "q_topic_messages")
public class Receiver4 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("topic receiver4 : " + hello);
    }
}