package com.hyit.www.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/shopadmin", method = RequestMethod.GET)
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        return "shop/shopOperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList() {
        return "shop/shopList";
    }

    /**
     * 店铺管理页面
     */
    @RequestMapping(value = "/shopmanagement", method = RequestMethod.GET)
    public String shopManagement() {
        return "shop/shopManagement";
    }

    /**
     * 商品类别管理页面
     */
    @GetMapping(value = "/productcategorymanagement")
    public String productCategoryManagement() {
        return "shop/productCategoryManagement";
    }

    @GetMapping(value = "/productoperation")
    public String productOperation() {
        return "shop/productOperation";
    }
}
