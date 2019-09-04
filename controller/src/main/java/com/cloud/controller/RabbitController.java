package com.cloud.controller;

import com.cloud.rabbit.order.OrderSender;
import com.cloud.rabbit.sample.Sender;
import com.cloud.rabbit.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WXY
 * @ClassName RabbitController
 * @Description 消息队列测试方法
 * @Date 2019/9/2 22:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private Sender sender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private OrderSender orderSender;

    @PostMapping("/simple")
    public void  simple(){
        for(int i=0;i<5;i++) {
            sender.send();
        }
    }

    @PostMapping("/topic")
    public void topic(){
        topicSender.send1();
        topicSender.send2();
    }

    @PostMapping("/order")
    public void order(){
        orderSender.send();
    }
}