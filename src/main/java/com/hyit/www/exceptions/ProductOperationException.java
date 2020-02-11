package com.hyit.www.exceptions;

/**
 * @Author LiJun
 * @Date 2020/2/11 12:15
 * 商品操作异常
 */

public class ProductOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductOperationException(String msg) {
        super(msg);
    }
}
