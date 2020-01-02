package com.hyit.www.dto;

import com.hyit.www.entity.Shop;
import com.hyit.www.enums.ShopStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopExecution {
    /**结果状态*/
    private int state;
    /**状态标识*/
    private String stateInfo;
    /**店铺数量*/
    private int count;
    /**操作的shop对象(crud时用到)*/
    private Shop shop;
    /**shop列表(查询店铺列表时用到)*/
    private List<Shop> shopList;
    /**默认构造函数*/
    public ShopExecution(){

    }
    /**针对店铺操作失败时的构造函数*/
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    /**针对店铺操作成功时的构造函数*/
    public ShopExecution(ShopStateEnum stateEnum,Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }
    /**针对店铺操作成功时的构造函数*/
    public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }
}
