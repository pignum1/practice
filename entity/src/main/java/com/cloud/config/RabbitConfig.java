package com.cloud.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WXY
 * @ClassName RabbitConfig
 * @Description T0D0
 * @Date 2019/8/24 17:39
 * @Version 1.0
 **/

@Configuration
public class RabbitConfig {
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}