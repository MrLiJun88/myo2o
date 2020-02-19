package com.hyit.www.exceptions;

/**
 * @Author LiJun
 * @Date 2020/2/19 16:38
 * @Description: 用户信息操作异常
 */

public class PersonInfoOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PersonInfoOperationException(String msg) {
        super(msg);
    }
}
