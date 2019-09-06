package test;

import com.alibaba.fastjson.JSON;
import com.cloud.Application;
import com.cloud.dao.UserDao;
import com.cloud.entity.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Autowired(required = true)
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserDao userDao;


    @Test
    public void testSave(){
// 保存对象
        List<User> users = userDao.findAll() ;
        System.out.println(users.size());

    }

    @Test
    public void testString(){
        User user = new User();
        user.setUsername("1");
        user.setPassword("2");
        stringRedisTemplate.opsForValue().set( user.getUsername(),user.toString() );

    }
}