package com.hyit.www.service;

import com.hyit.www.entity.ProductCategory;

import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/10 13:04
 */

public interface ProductCategoryService {
    /**
     * 通过shopId,查询指定某个店铺下的所有商品列表信息
     */
    List<ProductCategory> getProductCategoryList(Long shopId);
}
