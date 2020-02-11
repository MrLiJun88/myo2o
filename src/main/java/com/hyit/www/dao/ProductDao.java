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

    /**
     * 根据商品Id查询商品详情
     * @param productId
     */
    Product queryProductById(long productId);

    /**
     * 更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

}
