package com.cloud.rabbit.sample;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author WXY
 * @ClassName Sender
 * @Description T0D0
 * @Date 2019/8/24 17:43
 * @Version 1.0
 **/
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        // 设置  routeKey,简单模式下，这个就是队列名
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}