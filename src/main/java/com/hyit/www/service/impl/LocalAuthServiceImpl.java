package com.hyit.www.service.impl;

import com.hyit.www.dao.LocalAuthDao;
import com.hyit.www.dto.LocalAuthExecution;
import com.hyit.www.entity.LocalAuth;
import com.hyit.www.enums.LocalAuthStateEnum;
import com.hyit.www.enums.OperationStatusEnum;
import com.hyit.www.exceptions.LocalAuthOperationException;
import com.hyit.www.service.LocalAuthService;
import com.hyit.www.util.MD5;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author LiJun
 * @Date 2020/2/18 20:35
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    @Autowired
    private LocalAuthDao localAuthDao;

    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
        return localAuthDao.queryLocalByUsernameAndPwd(username, MD5.getMd5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUsername(String username) {
        return localAuthDao.queryLocalByUsername(username);
    }


    @Override
    public LocalAuth queryLocalByLocalAuthId(long localAuthId) {
        return localAuthDao.queryLocalByLocalAuthId(localAuthId);
    }


    @Override
    @Transactional
    public LocalAuthExecution saveLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        // 空值判断
        if (localAuth == null || localAuth.getUsername() == null || localAuth.getPassword() == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
        // 保存数据
        localAuth.setCreateTime(new Date());
        localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
        try {
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);
            if (effectedNum <= 0) {
                throw new LocalAuthOperationException("用户账号新增失败");
            } else {
                return new LocalAuthExecution(OperationStatusEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("insertLocalAuth error:" + e.getMessage());
        }
    }


    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(String username, String password, String newPassword)
            throws LocalAuthOperationException {
        // 非空判断
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)
                && StringUtils.isNotBlank(newPassword)) {
            try {
                int effectedNum = localAuthDao.updateLocalAuth(username, MD5.getMd5(password), MD5.getMd5(newPassword),
                        new Date());
                if (effectedNum <= 0) {
                    return new LocalAuthExecution(LocalAuthStateEnum.ERROR_UPDATE);
                } else {
                    return new LocalAuthExecution(OperationStatusEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new LocalAuthOperationException("updateLocalAuth error:" + e.getMessage());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }
}
