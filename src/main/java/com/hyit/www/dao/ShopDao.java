package com.hyit.www.dao;

import com.hyit.www.entity.Shop;

public interface ShopDao {
    /**-1:插入失败，1：插入成功*/
    int insertShop(Shop shop);
}
