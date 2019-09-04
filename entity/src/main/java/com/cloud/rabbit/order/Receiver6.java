package com.cloud.rabbit.order;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author WXY
 * @ClassName Receiver6
 * @Description T0D0
 * @Date 2019/9/2 23:42
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "q_fanout_B")
public class Receiver6 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("order receiver6  : " + hello + "/n");
    }
}