package com.hyit.www.service;

import com.hyit.www.BaseTest;
import com.hyit.www.dto.LocalAuthExecution;
import com.hyit.www.entity.LocalAuth;
import com.hyit.www.entity.PersonInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @Author LiJun
 * @Date 2020/2/18 20:49
 */

public class LocalAuthServiceTest extends BaseTest {

    @Autowired
    private LocalAuthService localAuthService;

    @Test
    public void testSaveLocalAuth(){
        // 新增一条本地用户
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setPersonInfo(personInfo);
        // 设置用户名和密码
        localAuth.setUsername("张三");
        localAuth.setPassword("helloworld");
        localAuth.setCreateTime(new Date());
        localAuth.setLastEditTime(new Date());
        LocalAuthExecution localAuthExecution = localAuthService.saveLocalAuth(localAuth);
        System.out.println(localAuthExecution.getLocalAuth().getUsername());
    }

    @Test
    public void testModifyLocalAuth(){
        LocalAuthExecution localAuthExecution = localAuthService.modifyLocalAuth("lijun", "hahahah", "shehui");
    }



}
