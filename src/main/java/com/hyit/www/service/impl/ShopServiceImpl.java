package com.hyit.www.service.impl;

import com.hyit.www.exceptions.ShopOperationException;
import com.hyit.www.dao.ShopDao;
import com.hyit.www.dto.ShopExecution;
import com.hyit.www.entity.Shop;
import com.hyit.www.enums.ShopStateEnum;
import com.hyit.www.service.ShopService;
import com.hyit.www.util.ImageUtil;
import com.hyit.www.util.PageCalculator;
import com.hyit.www.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Transactional
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

    @Override
    @Transactional
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    /**
     * 更新店铺信息
     */
    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, MultipartFile shopImg) throws ShopOperationException {

        if(null == shop || null == shop.getShopId()){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        else {
            try {
                // 判断是否要处理照片
                if (shopImg != null) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        // 删除原先图片
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    // 添加新照片
                    addShopImg(shop, shopImg);
                }
                // 更新照片信息
                shop.setLastEditTime(new Date());
                int effectNum = shopDao.updateShop(shop);
                // 更新成功
                if (effectNum > 0) {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                } else {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error " + e.getMessage());
            }
        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) throws ShopOperationException {
        // 前台页面插入的pageIndex（第几页）， 而dao层是使用 rowIndex （第几行） ，所以需要转换一下
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = new ArrayList<Shop>(16);
        ShopExecution se = new ShopExecution();
        // 查询带有分页的shopList
        shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        // 查询符合条件的shop总数
        int count = shopDao.queryShopCount(shopCondition);
        // 将shopList和 count设置到se中，返回给控制层
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    private void addShopImg(Shop shop, MultipartFile  shopImg) {
        /**1.获取shop图片目录的相对值路径*/
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg,dest);
        shop.setShopImg(shopImgAddr);
    }
}
