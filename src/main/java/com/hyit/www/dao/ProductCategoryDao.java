package com.hyit.www.dao;

import com.hyit.www.entity.ProductCategory;

import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/10 12:48
 */

public interface ProductCategoryDao {
    /**
     * 通过shopId查询店铺商品类型信息
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

}
