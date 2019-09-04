package com.cloud.rabbit.order;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author WXY
 * @ClassName Receiver7
 * @Description T0D0
 * @Date 2019/9/2 23:42
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "q_fanout_C")
public class Receiver7 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("order receiver7  : " + hello + "/n");
    }
}