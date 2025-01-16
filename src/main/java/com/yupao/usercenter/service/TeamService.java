package com.yupao.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupao.usercenter.model.domain.Team ;
import com.yupao.usercenter.model.domain.User;
import com.yupao.usercenter.model.dto.TeamQuery;
import com.yupao.usercenter.model.request.TeamJoinRequest;
import com.yupao.usercenter.model.request.TeamQuitRequest;
import com.yupao.usercenter.model.request.TeamUpdateRequest;
import com.yupao.usercenter.model.vo.TeanUserVO;


import java.util.List;

/**
 * 队伍服务
 *
 */
public interface TeamService extends IService<Team> {

    /**
     * 创建用户
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);


    /**
     * 搜索
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeanUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 更新队伍
     * @param teamUpdateRequest
     * @param loginUser
     * @return
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);


    /**
     * 加入队伍
     * @param teamJoinRequest
     * @return
     */
    boolean jointeam(TeamJoinRequest teamJoinRequest, User loginUser);


    /**
     * 退出队伍
     * @param teamQuitRequest
     * @param loginUser
     * @return
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);


    /**
     * 删除/解散 队伍
     * @param id
     * @return
     */
    boolean deleteTeam(Long id, User loginUser);
}
