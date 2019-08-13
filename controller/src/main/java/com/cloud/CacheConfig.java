package com.cloud;

import com.cloud.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Arrays;

/**
 * @author WXY
 * @ClassName CacheConfig
 * @Description 注解类缓存的配置类
 * @Date 2019/8/13 16:14
 * @Version 1.0
 **/
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager( redisTemplate );
        // 多个缓存的名称,目前只定义了一个
        rcm.setCacheNames( Arrays.asList( "thisredis" ) );
        //设置缓存过期时间(秒)
        rcm.setDefaultExpiration( 600 );
        return rcm;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate( factory );
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer( Object.class );
        ObjectMapper om = new ObjectMapper();
        om.setVisibility( PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY );
        om.enableDefaultTyping( ObjectMapper.DefaultTyping.NON_FINAL );
        jackson2JsonRedisSerializer.setObjectMapper( om );
        template.setValueSerializer( jackson2JsonRedisSerializer );
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("47.102.99.93");
        factory.setPort(6379);
        factory.setTimeout(0); //设置连接超时时间
        return factory;
    }


    @Bean
    public User user() {
        return new User();
    }



    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register( CacheConfig.class );
        ctx.refresh();
        User user = ctx.getBean(User.class);
        System.out.println(user.getUser( 1 ));
        System.out.println(user.getUser( 1 ));
        System.out.println(user.getUser( 2 ));
        ctx.close();
    }
}