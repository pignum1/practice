package test;
import java.util.Date;

import com.cloud.Application;
import com.cloud.dao.UserDao;
import com.cloud.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WXY
 * @ClassName PriceTest
 * @Description T0D0
 * @Date 2019/6/26 9:49
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("test2");
        user.setPassword("test2");
        user.setEmail("test2");
        user.setPhone("test2");
        user.setQuestion("test2");
        user.setAnswer("test2");
        user.setRole(0);
        user.setId("test2");
        user.setCreateTime(new Date());
        user.setCreateBy(0L);
        user.setLastModifiedTime(new Date());
        user.setLastModifiedBy("test2");



        userDao.save( user );

    }


}