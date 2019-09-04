package com.cloud.rabbit.order;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author WXY
 * @ClassName OrderSender
 * @Description T0D0
 * @Date 2019/9/2 23:42
 * @Version 1.0
 **/
@Component
public class OrderSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hi, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("fanout_exchange","", context);
    }

}