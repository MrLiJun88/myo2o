package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/11 11:55
 */

public class ProductImgDaoTest extends BaseTest {

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testBatchInsertProductImg(){
        // ��productIdΪ1����Ʒ���������ͼƬ��¼
        ProductImg productImg1 = new ProductImg();
        productImg1.setCreateTime(new Date());
        productImg1.setImgAddr("ͼƬ1");
        productImg1.setImgDesc("����ͼƬ1");
        productImg1.setPriority(1);
        productImg1.setProductId(1L);
        ProductImg productImg2 = new ProductImg();
        productImg2.setCreateTime(new Date());
        productImg2.setImgAddr("ͼƬ2");
        productImg2.setImgDesc("����ͼƬ2");
        productImg2.setPriority(2);
        productImg2.setProductId(1L);

        List<ProductImg> productImgs = new ArrayList<>();
        productImgs.add(productImg1);
        productImgs.add(productImg2);
        int effectNum = productImgDao.batchInsertProductImg(productImgs);
        System.out.println("effectNum:" + effectNum);
    }

    @Test
    public void testDeleteProductImgByProductId(){
        // ɾ����Ʒ�����¼
        Long productId = 8L;
        int effectNum = productImgDao.deleteProductImgByProductId(productId);
        System.out.println("effectNum:" + effectNum);
    }
}
