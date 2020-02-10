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

    /**
     * 批量添加商品类别
     * @param productCategoryList 商品类别列表
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

}
