package com.hyit.www.dao;

import com.hyit.www.entity.Product;

/**
 * @Author LiJun
 * @Date 2020/2/11 11:43
 */

public interface ProductDao {
    /**
     * 添加商品
     */
    int insertProduct(Product product);
}
