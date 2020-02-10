package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/10 12:58
 */

public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryProductCategoryList(){
        long shopId = 1L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        productCategoryList.stream().forEach(e -> System.out.println(e));
    }
}
