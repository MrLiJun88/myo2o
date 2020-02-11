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

    /**
     * 根据商品Id查询商品详情
     *
     * @param productId 商品ID
     */
    Product getProductById(long productId);

    /**
     * 修改商品信息以及图片处理
     *
     * @param product        商品信息
     * @param productImg     商品缩略图
     * @param productImgList 商品图片列表
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, MultipartFile productImg, List<MultipartFile> productImgList)
            throws ProductOperationException;

    /**
     * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺Id，商品类别
     * @param productCondition 查询条件
     * @param pageIndex        页码
     * @param pageSize         每页条数
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
