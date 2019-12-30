package com.hyit.www.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Area {
    private Integer areaId;
    private String areaName;
    /**权重，用来做区域排名*/
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
}
