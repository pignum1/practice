package test;

import com.cloud.Application;
import com.cloud.enums.Events;
import com.cloud.enums.States;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WXY
 * @ClassName StateMachineTest
 * @Description T0D0
 * @Date 2019/9/4 16:48
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StateMachineTest {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Test
    public void test(){
        stateMachine.start();
        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);
    }
}