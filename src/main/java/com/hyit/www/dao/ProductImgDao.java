package com.hyit.www.dao;

import com.hyit.www.entity.ProductImg;

import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/11 11:47
 */

public interface ProductImgDao {
    /**
     * 批量插入商品图片
     * @param productImgList 商品图片列表
     */
    int batchInsertProductImg(List<ProductImg> productImgList);
}
