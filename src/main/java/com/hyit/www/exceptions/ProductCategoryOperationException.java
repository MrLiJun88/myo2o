package com.hyit.www.exceptions;

/**
 * @Author LiJun
 * @Date 2020/2/10 15:05
 * 商品类别操作异常
 */

public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductCategoryOperationException(String msg) {
        super(msg);
    }
}
