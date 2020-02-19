package com.hyit.www.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class LocalAuth {
    private Long localAuthId;
    private Long userId;
    private String username;
    private String password;
    private Date createTime;
    private Date lastEditTime;
    private PersonInfo personInfo;
}
