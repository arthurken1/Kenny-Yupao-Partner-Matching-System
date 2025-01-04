package com.yupi.usercenter.controller;


import com.yupi.usercenter.service.TeamService;
import com.yupi.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/team")
@CrossOrigin(origins = {"https://localhost:3000"})
@Slf4j
public class TeamController {

    @Resource
    private UserService userService;

    @Resource
    private TeamService teamService;


}
