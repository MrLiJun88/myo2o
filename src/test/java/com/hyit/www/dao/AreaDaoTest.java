package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryAreas(){
        List<Area> areaList = areaDao.queryAreas();
        assertEquals(2,areaList.size());
    }
}
