package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;



    @Test
    void test(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("test","test");
        valueOperations.set("yupao1","123");
        valueOperations.set("yupao2",223);
        User user = new User();
        user.setId(1L);
        user.setUsername("yupao");
        valueOperations.set("yupao3",user);

        Object yupao = valueOperations.get("yupao1");
        System.out.println(yupao);
    }


}
