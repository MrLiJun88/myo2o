package com.hyit.www.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String LineLink;
    private String LineImg;
    private Integer priority;
    /**0.不可用，1.可用*/
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;

}
