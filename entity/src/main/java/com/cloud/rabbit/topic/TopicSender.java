package com.cloud.rabbit.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author WXY
 * @ClassName Sender
 * @Description T0D0
 * @Date 2019/9/2 23:16
 * @Version 1.0
 **/
@Component
public class TopicSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topic_exchange", "topic.message", context);
    }

    public void send2(){
        String context = "hi, i am message 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topic_exchange", "topic.messages", context);
    }

}