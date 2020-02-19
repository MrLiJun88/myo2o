package com.hyit.www.service.impl;

import com.hyit.www.dao.PersonInfoDao;
import com.hyit.www.dto.PersonInfoExecution;
import com.hyit.www.entity.PersonInfo;
import com.hyit.www.enums.OperationStatusEnum;
import com.hyit.www.enums.PersonInfoStateEnum;
import com.hyit.www.exceptions.PersonInfoOperationException;
import com.hyit.www.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author LiJun
 * @Date 2020/2/19 16:37
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public PersonInfo queryInfoByUserId(long userId) {
        return personInfoDao.queryInfoByUserId(userId);
    }


    @Override
    public PersonInfoExecution insertPersonInfo(PersonInfo user) {
        // 空值判断
        if (user == null || user.getLocalAuthId() == null || user.getName() == null) {
            return new PersonInfoExecution(PersonInfoStateEnum.NULL_PERSON_INFO);
        }
        // 设置默认信息
        user.setCreateTime(new Date());
        try {
            int effectedNum = personInfoDao.insertPersonInfo(user);
            if (effectedNum <= 0) {
                throw new PersonInfoOperationException("用户信息新增失败");
            } else {
                return new PersonInfoExecution(OperationStatusEnum.SUCCESS, user);
            }
        } catch (Exception e) {
            throw new PersonInfoOperationException("insertPersonInfo error:" + e.getMessage());
        }
    }


    @Override
    public PersonInfoExecution updatePersonInfo(PersonInfo user) {
        // 空值判断
        if (user == null || user.getUserId() == null || user.getLocalAuthId() == null || user.getName() == null) {
            return new PersonInfoExecution(PersonInfoStateEnum.NULL_PERSON_INFO);
        }
        // 设置默认信息
        user.setLastEditTime(new Date());
        try {
            int effectedNum = personInfoDao.updatePersonInfo(user);
            if (effectedNum <= 0) {
                throw new PersonInfoOperationException("用户信息修改失败");
            } else {
                return new PersonInfoExecution(OperationStatusEnum.SUCCESS, user);
            }
        } catch (Exception e) {
            throw new PersonInfoOperationException("insertPersonInfo error:" + e.getMessage());
        }
    }
}
