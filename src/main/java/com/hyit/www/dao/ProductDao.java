package com.hyit.www.dao;

import com.hyit.www.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/11 11:43
 */

public interface ProductDao {
    /**
     * 添加商品
     */
    int insertProduct(Product product);

    /**
     * 根据商品Id查询商品详情
     * @param productId
     */
    Product queryProductById(long productId);

    /**
     * 更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 查询商品列表并分页，输入条件：商品名（模糊），商品状态，店铺Id，商品类别
     * @param productCondition 查询条件
     * @param rowIndex         行数
     * @param pageSize         每页数
     */
    List<Product> selectProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
                                    @Param("pageSize") int pageSize);

    /**
     * 根据条件查询商品总数
     * @param productCondition 查询条件
     */
    int selectProductCount(@Param("productCondition") Product productCondition);

    /**
     * 删除商品类别时将商品记录中的类别项置空
     *
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);

}
