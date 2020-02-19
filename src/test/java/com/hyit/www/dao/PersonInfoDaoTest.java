package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.PersonInfo;
import com.hyit.www.enums.PersonInfoStatusEnum;
import com.hyit.www.enums.PersonInfoTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author LiJun
 * @Date 2020/2/19 16:24
 */

public class PersonInfoDaoTest extends BaseTest {
    @Autowired
    private PersonInfoDao personInfoDao;

    /**
     * 测试插入用户
     *
     * @throws Exception
     */
    @Test
    public void testInsertPersonInfo() throws Exception {
        // 新增一条用户
        PersonInfo personInfo = new PersonInfo();
        personInfo.setLocalAuthId(1L);
        // 注册一个有权限访问的顾客
        personInfo.setEnableStatus(PersonInfoStatusEnum.ALLOW.getState());
        personInfo.setUserType(PersonInfoTypeEnum.CUSTOMER.getState());
        personInfo.setCreateTime(new Date());
        personInfo.setGender("男");
        personInfo.setEmail("a@a.com");
        personInfo.setName("顾客a");
        int effectNum = personInfoDao.insertPersonInfo(personInfo);
    }

    /**
     * 根据用户Id查询本地账号信息
     */
    @Test
    public void testQueryInfoByUserId() {
        PersonInfo personInfo = personInfoDao.queryInfoByUserId(1L);
        System.out.println(personInfo.getName());
    }

    /**
     * 更新账号信息
     */
    @Test
    public void testUpdatePersonInfo() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setLastEditTime(new Date());
        personInfo.setUserId(1L);
        personInfo.setName("李四");
        int effectNum = personInfoDao.updatePersonInfo(personInfo);
    }
}
