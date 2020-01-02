package com.hyit.www.dao;

import com.hyit.www.BaseTest;
import com.hyit.www.entity.Area;
import com.hyit.www.entity.PersonInfo;
import com.hyit.www.entity.Shop;
import com.hyit.www.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(1);
        shop.setShopId(1L);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setAdvice("审核中");
        shop.setPhone("110");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setShopAddr("nanjing");
        int effectNum = shopDao.insertShop(shop);
        assertEquals(1,effectNum);
    }

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("俊俊的小店");
        shop.setShopDesc("小店8折呦");
        shop.setAdvice("最近8太行");
        shop.setLastEditTime(new Date());
        int effectNum = shopDao.updateShop(shop);
        assertEquals(1,effectNum);
    }
}
