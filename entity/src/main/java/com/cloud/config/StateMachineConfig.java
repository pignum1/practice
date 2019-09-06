package com.cloud.config;

import com.cloud.enums.Events;
import com.cloud.enums.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @author WXY
 * @ClassName StateMachineConfig
 * @Description 状态机
 *              通过org.springframework.statemachine实现,配置文件好多呀
 * @Date 2019/9/4 15:26
 * @Version 1.0
 **/
@Configuration
@EnableStateMachine // StateMachine 开启状态机
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    private Logger logger = LoggerFactory.getLogger( getClass() );


    /**
     * 初始化状态机的状态 && 状态机的所有可取值
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.UNPAID) //定义初始化状态值
                .states( EnumSet.allOf(States.class)); //定义状态的所有的可以去之范围
    }


    /**
     * 定义迁移机的迁移动作有哪些 并指定来源状态 && 目标状态
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_FOR_RECEIVE)//事件状态的改变
                .event(Events.PAY) // 触发事件
                .and()
                .withExternal()
                .source(States.WAITING_FOR_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE);
    }


    /**
     * 指定监听器
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .listener(listener());	// 指定状态机的处理监听器
    }


    /**
     * 监听器规则  状态迁移的处理逻辑
     * @return
     */
    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void transition(Transition<States, Events> transition) {
                if(transition.getTarget().getId() == States.UNPAID) {
                    logger.info("订单创建，待支付");//监听事件的逻辑
                    return;
                }

                if(transition.getSource().getId() == States.UNPAID
                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
                    logger.info("用户完成支付，待收货");
                    return;
                }

                if(transition.getSource().getId() == States.WAITING_FOR_RECEIVE
                        && transition.getTarget().getId() == States.DONE) {
                    logger.info("用户已收货，订单完成");
                    return;
                }
            }

        };
    }
}