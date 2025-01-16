package com.yupao.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupao.usercenter.mapper.UserTeamMapper ;
import com.yupao.usercenter.model.domain.UserTeam ;
import com.yupao.usercenter.service.UserTeamService ;
import org.springframework.stereotype.Service;

/**
 * 用户队伍服务实现类
 *
 */
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
        implements UserTeamService {

}




