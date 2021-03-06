package com.hyit.www.service;

import com.hyit.www.dto.ProductCategoryExecution;
import com.hyit.www.entity.ProductCategory;
import com.hyit.www.exceptions.ProductCategoryOperationException;

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

    /**
     * 批量添加商品类别
     *
     * @param productCategoryList
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品里的类别id置空，再删除该商品类别
     *
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;
}
