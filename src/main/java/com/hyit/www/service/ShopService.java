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
}
