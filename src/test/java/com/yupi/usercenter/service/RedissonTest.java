package com.yupi.usercenter.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class RedissonTest {


    @Resource
    private RedissonClient redissonClient;

    @Test
    void test(){

        RList<Object> rList = redissonClient.getList("test-list");
        rList.add("test");
        System.out.println(rList.get(0));


        RMap<Object, Object> rMap = redissonClient.getMap("test-map");
        rMap.put("test", "test");
        System.out.println(rMap.get(0));

    }
}
