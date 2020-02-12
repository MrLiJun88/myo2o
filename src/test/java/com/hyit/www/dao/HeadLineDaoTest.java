package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/12 16:59
 */

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testSelectHeadLineList(){
        List<HeadLine> headLines = headLineDao.selectHeadLineList(new HeadLine());
        System.out.println(headLines.size());
    }

}
