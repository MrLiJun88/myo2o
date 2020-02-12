package com.hyit.www.controller.frontend;

import com.hyit.www.entity.HeadLine;
import com.hyit.www.entity.ShopCategory;
import com.hyit.www.enums.EnableStatusEnum;
import com.hyit.www.service.HeadLineService;
import com.hyit.www.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author LiJun
 * @Date 2020/2/12 17:09
 */
@RestController
@RequestMapping(value = "/front")
public class MainPageController {

    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private HeadLineService headLineService;

    /**
     * 初始化前端展示系统的主页信息，包括获取一级店铺类别列表一级头条列表
     */
    @GetMapping(value = "/listMainPageInfo")
    public Map<String, Object> listMainPageInfo() {
        Map<String, Object> modelMap = new HashMap<>(16);
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        try {
            // 获取一级店铺列表（即parentId为空的shopCategory）
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList", shopCategoryList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        // 构建头条列表
        List<HeadLine> headLineList = new ArrayList<>();
        try {
            // 获取状态可用的头条列表
            HeadLine headLineCondition = new HeadLine();
            headLineCondition.setEnableStatus(EnableStatusEnum.AVAILABLE.getState());
            headLineList = headLineService.getHeadLineList(headLineCondition);
            modelMap.put("headLineList", headLineList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        modelMap.put("success", true);
        return modelMap;
    }
}
