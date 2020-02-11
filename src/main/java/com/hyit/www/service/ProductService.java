package com.hyit.www.service;

import com.hyit.www.dto.ProductExecution;
import com.hyit.www.entity.Product;
import com.hyit.www.exceptions.ProductOperationException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/11 12:11
 */

public interface ProductService {
    /**
     * 添加商品信息以及图片处理
     *
     * @param product        商品信息
     * @param productImg     商品缩略图
     * @param productImgList 商品图片列表
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, MultipartFile productImg, List<MultipartFile> productImgList)
            throws ProductOperationException;
}
