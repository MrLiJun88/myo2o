package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/10 12:58
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按方法名字母顺序执行
public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testAQueryProductCategoryList(){
        long shopId = 1L;
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
        productCategoryList.stream().forEach(e -> System.out.println(e));
    }

    @Test
    public void testBBatchInsertProductCategory(){
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

    @Test
    public void testCDeleteProductCategory(){
        long shopId = 1L;
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);
        for (ProductCategory productCategory : productCategories) {
            if ("1111".equals(productCategory.getProductCategoryName()) || "2222".equals(productCategory.getProductCategoryName())) {
                int effectedNum = productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(),
                        shopId);
            }
        }
    }

}
