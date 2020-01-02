package com.hyit.www.service;

import com.hyit.www.dto.ShopExecution;
import com.hyit.www.entity.Shop;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {
    /**添加店铺*/
    ShopExecution addShop(Shop shop, MultipartFile shopImg);
}
