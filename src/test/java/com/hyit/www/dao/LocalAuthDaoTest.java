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
     * ���Բ����û�
     */
    @Test
    public void testInsertLocalAuth() throws Exception {
        // ����һ�������û�
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setPersonInfo(personInfo);
        // �����û���������
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setCreateTime(new Date());
        localAuth.setLastEditTime(new Date());
        int effectNum = localAuthDao.insertLocalAuth(localAuth);
        System.out.println(effectNum);
    }

    /**
     * �����û������˺Ų�ѯ�û�
     */
    @Test
    public void testQueryLocalByUsernameAndPwd() {
        LocalAuth localAuth = localAuthDao.queryLocalByUsernameAndPwd(username, password);
        System.out.println(localAuth);
    }

    /**
     * �����û�Id��ѯ�����˺���Ϣ
     */
    @Test
    public void testQueryLocalByLocalAuthId() {
        LocalAuth localAuth = localAuthDao.queryLocalByLocalAuthId(1L);
        System.out.println(localAuth);
    }

    /**
     * �����˺���Ϣ
     */
    @Test
    public void testUpdateLocalAuth() {
        Date now = new Date();
        int effectNum = localAuthDao.updateLocalAuth("lijun", "654321", "hahahah", now);
    }

}
