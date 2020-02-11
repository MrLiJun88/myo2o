package com.hyit.www.service.impl;

import com.hyit.www.dao.ProductCategoryDao;
import com.hyit.www.dto.ProductCategoryExecution;
import com.hyit.www.entity.ProductCategory;
import com.hyit.www.enums.OperationStatusEnum;
import com.hyit.www.enums.ProductCategoryStateEnum;
import com.hyit.www.exceptions.ProductCategoryOperationException;
import com.hyit.www.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/10 13:05
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;


    @Override
    @Transactional
    public List<ProductCategory> getProductCategoryList(Long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        // 列表不为空
        if (productCategoryList != null && !productCategoryList.isEmpty()) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                //添加失败，则抛出自定义异常
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(OperationStatusEnum.SUCCESS, productCategoryList, effectedNum);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException(
                        ProductCategoryStateEnum.EDIT_ERROR.getStateInfo() + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPETY_LIST);
        }
    }


    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        /** 删除商品类别时将商品记录中的类别项置空
        try {
            int effectNum = productDao.updateProductCategoryToNull(productCategoryId);
            if (effectNum < 0) {
                throw new ProductCategoryOperationException(ProductCategoryStateEnum.EDIT_ERROR.getStateInfo());
            }
        } catch (ProductCategoryOperationException e) {
            throw new ProductCategoryOperationException("deleteProductCategory error" + e.getMessage());
        } */

        // 删除商品类别
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException(ProductCategoryStateEnum.DELETE_ERROR.getStateInfo());
            } else {
                return new ProductCategoryExecution(OperationStatusEnum.SUCCESS, null, effectedNum);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error" + e.getMessage());
        }
    }
}
