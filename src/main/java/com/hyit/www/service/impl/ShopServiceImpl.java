package com.hyit.www.service.impl;

import com.hyit.exceptions.ShopOperationException;
import com.hyit.www.dao.ShopDao;
import com.hyit.www.dto.ShopExecution;
import com.hyit.www.entity.Shop;
import com.hyit.www.enums.ShopStateEnum;
import com.hyit.www.service.ShopService;
import com.hyit.www.util.ImageUtil;
import com.hyit.www.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Transient
    @Override
    public ShopExecution addShop(Shop shop, MultipartFile shopImg) {
        /**空值判断*/
        if(null == shop){
            return new ShopExecution(ShopStateEnum.NULL_SHOPID);
        }
        try{
            /**1.向数据库中插入店铺，并赋初始值*/
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectNum = shopDao.insertShop(shop);
            if(effectNum <= 0){
                throw new ShopOperationException("店铺创建失败");
            }
            else {
                if(null != shopImg){
                    /**存储图片*/
                    try{
                        addShopImg(shop,shopImg);
                    }
                    catch (Exception e){
                        throw new  ShopOperationException("addShopImg error: " + e.getMessage());
                    }
                    /**更新店铺的图片地址*/
                    effectNum = shopDao.updateShop(shop);
                    if(effectNum <= 0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }
        catch (Exception e){
            throw new ShopOperationException("addShop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, MultipartFile  shopImg) {
        /**1.获取shop图片目录的相对值路径*/
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg,dest);
        shop.setShopImg(shopImgAddr);
    }
}
