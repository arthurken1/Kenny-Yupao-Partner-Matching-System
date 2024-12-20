package com.yupi.usercenter.job;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存预热
 */
@Component
@Slf4j
public class PreCacheJob {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    private List<Long> mainUserList = Arrays.asList(1L);

    @Scheduled(cron = "0 */5 * * * *")
    public void doCacheRecommenduser() {
        for (Long userId : mainUserList) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            Page<User> userPage = userService.page(new Page<>(1,20),queryWrapper);
            String redisKey = String.format("shayu:user:recommend:%s",userId);
            ValueOperations valueOperations = redisTemplate.opsForValue();
            //写缓存,30s过期
            try {
                valueOperations.set(redisKey,userPage,30000, TimeUnit.MILLISECONDS);
            } catch (Exception e){
                log.error("redis set key error",e);
            }
        }

    }

}
