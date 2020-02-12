package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.ShopCategory;
import com.hyit.www.util.ImageUtil;
import com.hyit.www.util.PathUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
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

    @Test
    public void testInsertShopCategory() throws IOException {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setCreateTime(new Date());
        shopCategory.setLastEditTime(new Date());
        shopCategory.setPriority(5);
        shopCategory.setShopCategoryName("二手市场");
        shopCategory.setShopCategoryDesc("二手市场");
        String filePath = "C:\\Users\\Administrator\\Desktop\\Gproject\\jpg2\\images\\item\\shopcategory\\aaa.png";
        MultipartFile multipartFile = ImageUtil.path2MultipartFile(filePath);
        String dest = PathUtil.getShopCategoryImagePath();
        String generateHeadImg = ImageUtil.generateShopCategoryImg(multipartFile, dest);
        shopCategory.setShopCategoryImg(generateHeadImg);
        
//        ShopCategory parentShopCategory = new ShopCategory();
//        parentShopCategory.setShopCategoryId(4L);
//        shopCategory.setParent(parentShopCategory);
        int effectNum = shopCategoryDao.insertShopCategory(shopCategory);
        System.out.println("effectNum:" + effectNum);
    }
}
