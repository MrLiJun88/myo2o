package com.hyit.www.service;

import com.hyit.www.dto.PersonInfoExecution;
import com.hyit.www.entity.PersonInfo;

/**
 * @Author LiJun
 * @Date 2020/2/19 16:35
 */

public interface PersonInfoService {
    /**
     * 根据用户ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    PersonInfo queryInfoByUserId(long userId);

    /**
     * 添加平台账号
     *
     * @param user 用户信息
     * @return 操作返回信息
     */
    PersonInfoExecution insertPersonInfo(PersonInfo user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 操作返回信息
     */
    PersonInfoExecution updatePersonInfo(PersonInfo user);
}
