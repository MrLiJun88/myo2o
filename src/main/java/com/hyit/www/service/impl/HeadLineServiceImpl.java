package com.hyit.www.service.impl;

import com.hyit.www.dao.HeadLineDao;
import com.hyit.www.entity.HeadLine;
import com.hyit.www.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/12 17:07
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {

    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.selectHeadLineList(headLineCondition);
    }
}
