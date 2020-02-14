package com.hyit.www.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author LiJun
 * @Date 2020/2/12 22:09
 */

@Controller
@RequestMapping(value = "/front")
public class FrontendController {
    /**
     * 前端首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "front/index";
    }

    /**
     * 店铺列表页路由
     */
    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public String shopList() {
        return "front/shopList";
    }

    /**
     * 店铺详情页路由
     */
    @RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
    public String shopDetail() {
        return "front/shopDetail";
    }

    /**
     * 商品详情页路由
     */
    @RequestMapping(value = "/productdetail", method = RequestMethod.GET)
    public String productDetail() {
        return "front/productDetail";
    }
}
