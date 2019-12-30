package com.hyit.www.dao;

import com.hyit.www.entity.Area;

import java.util.List;

public interface AreaDao {
    /**列出所有的区域列表*/
    List<Area> queryAreas();
}
