package com.yupi.usercenter.once;

import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.model.domain.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

//此处是一个练习方法，仅展示如何使用定时任务来导入数据，不太好


@Component
public class InsertUsers {

    @Resource
    private UserMapper userMapper;

    //以下注解取消掉，就不会开启了
    //@Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE)
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_USERS = 10000000;
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

            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
