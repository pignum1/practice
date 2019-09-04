package test;

import com.cloud.Application;
import com.cloud.rabbit.sample.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WXY
 * @ClassName RabbitTest
 * @Description T0D0
 * @Date 2019/9/2 22:21
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RabbitTest {
    @Autowired
    private Sender sender;

    @Test
    public void sampleTest() {
        for(int i=0;i<30;i++) {
            sender.send();
        }
    }
}