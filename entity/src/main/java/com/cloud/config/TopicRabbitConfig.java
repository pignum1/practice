package com.cloud.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WXY
 * @ClassName TopicConfig
 * @Description 主题模式(匹配符号)
 * @Date 2019/9/2 23:08
 * @Version 1.0
 **/
@Configuration
public class TopicRabbitConfig {

    /**
     * 简单模式下的消息队列配置，默认是按劳分配得队列获取
     *
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue( "hello" );
    }

//---------------------分割线   主题模式--------------------------------//

    /**
     * 定义两个消息队列
     */
    private final static String message = "q_topic_message";
    private final static String messages = "q_topic_messages";
    private final static String exchange = "topic_exchange";

    @Bean
    public Queue queueMessage() {
        return new Queue( TopicRabbitConfig.message );
    }

    @Bean
    public Queue queueMessages() {
        return new Queue( TopicRabbitConfig.messages );
    }

    /**
     * 声明一个Topic类型的交换机
     *
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange( exchange );
    }

    /**
     * queueMessage绑定到交换机上，并设置匹配得规则
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange) {
        return BindingBuilder.bind( queueMessage ).to( exchange ).with( "topic.message" );
    }

    /**
     * queueMessage绑定到交换机上，并设置匹配得规则
     * - 是匹配一个或多个词   *只匹配一个
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages,TopicExchange exchange) {
        return BindingBuilder.bind( queueMessages ).to( exchange ).with( "topic.-" );
    }

    //------------------------分割线     订阅模式----------------------------------//

    //订阅模式下得配置与主题模式基本相同，区别是不添加匹配规则
    @Bean
    public Queue aMessage() {
        return new Queue( "q_fanout_A" );
    }

    @Bean
    public Queue bMessage() {
        return new Queue( "q_fanout_B" );
    }

    @Bean
    public Queue cMessage() {
        return new Queue( "q_fanout_C" );
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange( "fanout_exchange" );
    }

    /**
     * 将交换机和队列绑定
     */
    @Bean
    Binding bindingExchangeA(Queue aMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind( aMessage ).to( fanoutExchange );
    }

    @Bean
    Binding bindingExchangeB(Queue bMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind( bMessage ).to( fanoutExchange );
    }

    @Bean
    Binding bindingExchangeC(Queue cMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind( cMessage ).to( fanoutExchange );
    }

}