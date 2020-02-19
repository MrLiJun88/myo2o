package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.LocalAuth;
import com.hyit.www.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author LiJun
 * @Date 2020/2/18 20:14
 */

public class LocalAuthDaoTest extends BaseTest {
    @Autowired
    private LocalAuthDao localAuthDao;
    private static final String username = "lijun";
    private static final String password = "lijun";

    /**
     * 测试插入用户
     */
    @Test
    public void testInsertLocalAuth() throws Exception {
        // 新增一条本地用户
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setPersonInfo(personInfo);
        // 设置用户名和密码
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setCreateTime(new Date());
        localAuth.setLastEditTime(new Date());
        int effectNum = localAuthDao.insertLocalAuth(localAuth);
        System.out.println(effectNum);
    }

    /**
     * 根据用户名和账号查询用户
     */
    @Test
    public void testQueryLocalByUsernameAndPwd() {
        LocalAuth localAuth = localAuthDao.queryLocalByUsernameAndPwd(username, password);
        System.out.println(localAuth);
    }

    /**
     * 根据用户Id查询本地账号信息
     */
    @Test
    public void testQueryLocalByLocalAuthId() {
        LocalAuth localAuth = localAuthDao.queryLocalByLocalAuthId(1L);
        System.out.println(localAuth);
    }

    /**
     * 更新账号信息
     */
    @Test
    public void testUpdateLocalAuth() {
        Date now = new Date();
        int effectNum = localAuthDao.updateLocalAuth("lijun", "654321", "hahahah", now);
    }

}
