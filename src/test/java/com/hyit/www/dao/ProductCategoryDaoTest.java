package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
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

    @Test
    public void testBatchInsertProductCategory(){
        ProductCategory pc1 = new ProductCategory();
        pc1.setCreateTime(new Date());
        pc1.setPriority(1);
        pc1.setShopId(1L);
        pc1.setProductCategoryName("商品类别1");

        ProductCategory pc2 = new ProductCategory();
        pc2.setCreateTime(new Date());
        pc2.setPriority(2);
        pc2.setShopId(1L);
        pc2.setProductCategoryName("商品类别2");
        List<ProductCategory> pcList = new ArrayList<>();
        pcList.add(pc1);
        pcList.add(pc2);
        int effectNum = productCategoryDao.batchInsertProductCategory(pcList);
        System.out.println(effectNum);
    }
}
