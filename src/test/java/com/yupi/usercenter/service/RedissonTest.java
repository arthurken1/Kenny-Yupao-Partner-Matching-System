package com.yupi.usercenter.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
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

    }
}
