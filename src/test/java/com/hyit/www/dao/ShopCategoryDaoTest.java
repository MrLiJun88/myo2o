package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){
//        ShopCategory shopCategory = new ShopCategory();
//        ShopCategory parentCategory = new ShopCategory();
//        parentCategory.setShopCategoryId(1L);
//        shopCategory.setParent(parentCategory);
//        List<ShopCategory> shopCategoryList2 = shopCategoryDao.queryShopCategory(shopCategory);
//        assertEquals(1,shopCategoryList2.size());
//        System.out.println(shopCategoryList2.get(0).getShopCategoryName());
        ShopCategory shopCategory = new ShopCategory();
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(shopCategory);
        assertEquals(2,shopCategoryList.size());
        System.out.println(shopCategoryList.get(1).getShopCategoryName());
    }
}
