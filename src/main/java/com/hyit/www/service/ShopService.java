package com.hyit.www.service;

import com.hyit.www.dto.ShopExecution;
import com.hyit.www.entity.Shop;
import com.hyit.www.exceptions.ShopOperationException;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {
    /**添加店铺*/
    ShopExecution addShop(Shop shop, MultipartFile shopImg);
    /**根据shopID获取店铺信息*/
    Shop getByShopId(long shopId);
    /**更新店铺信息，包含对店铺图片的更新*/
    ShopExecution modifyShop(Shop shop, MultipartFile shopImg) throws ShopOperationException;
    /**
     * 获取店铺分页列表
     * @param shopCondition 店铺查询条件
     * @param pageIndex     第几页
     * @param pageSize      每页条数
     * @return
     * @throws ShopOperationException
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) throws ShopOperationException;
}
