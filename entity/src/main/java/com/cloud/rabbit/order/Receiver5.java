package com.cloud.rabbit.order;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author WXY
 * @ClassName Receiver5
 * @Description T0D0
 * @Date 2019/9/2 23:41
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "q_fanout_A")
public class Receiver5 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("order receiver5  : " + hello + "/n");
    }
}