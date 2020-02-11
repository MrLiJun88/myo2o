package com.hyit.www.service;

import com.hyit.www.BaseTest;
import com.hyit.www.dto.ProductExecution;
import com.hyit.www.entity.Product;
import com.hyit.www.entity.ProductCategory;
import com.hyit.www.entity.Shop;
import com.hyit.www.enums.EnableStatusEnum;
import com.hyit.www.util.ImageUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/11 12:40
 */

public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws IOException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        product.setShop(shop);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1描述");
        product.setPriority(20);
        product.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
        product.setLastEditTime(new Date());
        product.setCreateTime(new Date());
        String filePath0 = "C:\\Users\\Administrator\\Desktop\\Gproject\\1.jpg";
        List<MultipartFile> productImgList = new ArrayList<>();
        String filePath1 = "C:\\Users\\Administrator\\Desktop\\Gproject\\2.jpg";
        MultipartFile productImg1 = ImageUtil.path2MultipartFile(filePath1);
        productImgList.add(productImg1);
        String filePath2 = "C:\\Users\\Administrator\\Desktop\\Gproject\\3.jpg";
        MultipartFile productImg2 = ImageUtil.path2MultipartFile(filePath2);
        productImgList.add(productImg2);
        ProductExecution se = productService.addProduct(product, ImageUtil.path2MultipartFile(filePath0),
                productImgList);
    }

    @Test
    public void testModifyProduct() throws IOException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(1L);
        product.setShop(shop);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);
        product.setProductId(9L);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品2");
        product.setProductDesc("测试商品2描述");
        product.setPriority(22);
        product.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
        product.setLastEditTime(new Date());
        product.setCreateTime(new Date());
        String filePath0 = "C:\\Users\\Administrator\\Desktop\\Gproject\\1.jpg";
        List<MultipartFile> productImgList = new ArrayList<>();
        String filePath1 = "C:\\Users\\Administrator\\Desktop\\Gproject\\2.jpg";
        MultipartFile productImg1 = ImageUtil.path2MultipartFile(filePath1);
        productImgList.add(productImg1);
        String filePath2 = "C:\\Users\\Administrator\\Desktop\\Gproject\\3.jpg";
        MultipartFile productImg2 = ImageUtil.path2MultipartFile(filePath2);
        productImgList.add(productImg2);

        ProductExecution se = productService.modifyProduct(product, ImageUtil.path2MultipartFile(filePath0),
                productImgList);
        System.out.println("ProductExecution.state" + se.getState());
        System.out.println("ProductExecution.stateInfo" + se.getStateInfo());
    }

}
