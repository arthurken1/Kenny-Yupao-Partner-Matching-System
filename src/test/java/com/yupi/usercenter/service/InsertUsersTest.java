package com.yupi.usercenter.service;

import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class InsertUsersTest {
    @Resource
    private UserService userService;

    //新建一个线程池，最下面的并发方法使用的默认线程池会限定核心线程数
    private ExecutorService executorService = new ThreadPoolExecutor(60, 1000, 10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));

    @Test
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final int INSERT_USERS = 1000;
        List<User> userList = new ArrayList<>();

        for (int i = 0; i < INSERT_USERS; i++) {
            User user = new User();

            user.setUsername("假kenny");
            user.setUserAccount("fake_kenny");
            user.setAvatarUrl("");
            user.setGender(0);
            user.setUserPassword("12345678");
            user.setPhone("");
            user.setEmail("");
            user.setUserStatus(0);
            user.setTags("[]");
            user.setUserRole(0);
            user.setPlanetCode("1111122222");
            userList.add(user);

        }

        userService.saveBatch(userList,100);


        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }


    @Test
    public void doConcurrencyInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_USERS = 10000;
        int batchSize = 5000;

        int j = 0;
        List<CompletableFuture> futures = new ArrayList<>();
        for (int i = 0; i < INSERT_USERS/batchSize; i++) {
            List<User> userList = new ArrayList<>();
            while (true){
                j++;
                User user = new User();
                user.setUsername("假kenny");
                user.setUserAccount("fake_kenny");
                user.setAvatarUrl("");
                user.setGender(0);
                user.setUserPassword("12345678");
                user.setPhone("");
                user.setEmail("");
                user.setUserStatus(0);
                user.setTags("[]");
                user.setUserRole(0);
                user.setPlanetCode("1111122222");
                userList.add(user);
                if(j % batchSize == 0){
                    break;
                }
            }
            //这里是把里面的任务封装成了实现callable或者runnable的类，lambda表达式隐式地实现了runnable接口
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("当前线程" + Thread.currentThread().getName());
                userService.saveBatch(userList, batchSize);
            }, executorService);
            futures.add(future);

        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();




        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
