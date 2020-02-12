package com.hyit.www.dao;

import com.hyit.www.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/12 16:53
 */

public interface HeadLineDao {
    /**
     * 根据传入的查询条件查询头条信息列表
     * @param headLineCondition
     */
    List<HeadLine> selectHeadLineList(@Param("headLineCondition") HeadLine headLineCondition);
}
