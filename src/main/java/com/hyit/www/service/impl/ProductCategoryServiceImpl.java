package com.hyit.www.service.impl;

import com.hyit.www.dao.ProductCategoryDao;
import com.hyit.www.entity.ProductCategory;
import com.hyit.www.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Transient
    public List<ProductCategory> getProductCategoryList(Long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }
}
