package com.hyit.www.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ProductCategory {
    private Long productCategoryId;
    private long shopId;
    private String productCategoryName;
    private Integer priority;
    private Date createTime;
}
