package com.hyit.www.service.impl;

import com.hyit.www.dao.AreaDao;
import com.hyit.www.entity.Area;
import com.hyit.www.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryAreas();
    }
}
