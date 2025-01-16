package com.yupao.usercenter.model.request;



import lombok.Data;

import java.io.Serializable;

@Data
public class TeamQuitRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 要更新的那条数据
     */
    private Long teamId;



}
