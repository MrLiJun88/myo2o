package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.Product;
import com.hyit.www.entity.ProductCategory;
import com.hyit.www.entity.ProductImg;
import com.hyit.www.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/11 11:55
 */

public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testInsertProduct(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        Product product = new Product();
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        product.setProductName("1");
        product.setProductDesc("11");
        product.setImgAddr("111");
        product.setNormalPrice("1111");
        product.setPromotionPrice("11111");
        product.setPriority(1);
        product.setProductCategory(productCategory);
        product.setShop(shop);
        int effectNum = productDao.insertProduct(product);
        System.out.println("effectNum:" + effectNum);
    }

    @Test
    public void testQueryProductByProductId() throws Exception {
        Long productId = 9L;
        Product product = productDao.queryProductById(productId);
        System.out.println(product);
        System.out.println("productImgSize：" + product.getProductImgList().size());
    }

    @Test
    public void testUpdateProduct(){
        Product product = new Product();
        product.setProductId(9L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        Shop shop = new Shop();
        shop.setShopId(1L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("你喜欢就好");
        int effectNum = productDao.updateProduct(product);
        System.out.println("effectNum:" + effectNum);
    }
}
