package com.hyit.www.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonInfo {
    private Long userId;
    private Long localAuthId;
    private String name;
    private String profileImg;
    private String email;
    private String gender;
    /**0.禁止使用本商城 1.允许使用本商城*/
    private Integer enableStatus;
    /**1.顾客 2.店家 3.超级管理员*/
    private Integer userType;
    private Date createTime;
    private Date lastEditTime;
}
