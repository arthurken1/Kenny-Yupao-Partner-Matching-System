package com.yupi.usercenter.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @Test
    void testWatchDog(){
        RLock lock = redissonClient.getLock("yupao:precachejob:docache:testlock");
        try {
            // 只有一个线程能获取到锁
            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)) {
                Thread.sleep(300000);//todo 实际要执行的代码
                System.out.println("getLock: " + Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            // 只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                System.out.println("unLock: " + Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}
