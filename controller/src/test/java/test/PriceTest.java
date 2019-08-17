package test;

import com.alibaba.fastjson.JSON;
import com.cloud.Application;
import com.cloud.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WXY
 * @ClassName PriceTest
 * @Description T0D0
 * @Date 2019/6/26 9:49
 * @Version 1.0
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceTest {

    @Autowired(required = true)
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testSave(){
// 保存对象
        User user = new User();
        user.setUsername("uhu");
        user.setPassword("13424");
        redisTemplate.opsForValue().set(user.getUsername(), user);
        Object object = redisTemplate.opsForValue().get( "1" );
        System.out.println(object.toString());

    }

    @Test
    public void testString(){
        User user = new User();
        user.setUsername("1");
        user.setPassword("2");
        stringRedisTemplate.opsForValue().set( user.getUsername(),user.toString() );

    }
}