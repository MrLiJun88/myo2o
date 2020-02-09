package com.hyit.www.dao;

import com.hyit.www.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * 分页查询店铺，可输入的条件(店铺名，店铺状态，店铺类别，区域id,owner)
     * @param shopCondition 条件封装成一个Shop对象
     * @param rowIndex：从第几行开始取
     * @param pageSize：返回多少行数据（页面上的数据量）
     *比如 rowIndex为1,pageSize为5 即为 从第一行开始取，取5行数据
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                              @Param("pageSize") int pageSize);
    /**根据shopId,查询店铺信息*/
    Shop queryByShopId(long shopId);
    /**-1:插入失败，1：插入成功*/
    int insertShop(Shop shop);
    /**更新店铺信息*/
    int updateShop(Shop shop);
    /**根据模糊查询，找出所有符合条件的Shop数量*/
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
