package com.hyit.www.service;

import com.hyit.www.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/12 17:06
 */

public interface HeadLineService {
    /**
     * 根据条件查询头条列表
     *
     * @param headLineCondition
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
