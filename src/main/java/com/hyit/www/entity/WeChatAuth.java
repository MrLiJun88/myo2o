package com.hyit.www.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeChatAuth {
    private Long weCharAuthId;
    private String openId;
    private Date createTime;
    private PersonInfo personInfo;
}
