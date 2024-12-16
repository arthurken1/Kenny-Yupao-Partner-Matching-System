package com.yupi.usercenter.service;

import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InsertUsersTest {
    @Resource
    private UserService userService;

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
}
