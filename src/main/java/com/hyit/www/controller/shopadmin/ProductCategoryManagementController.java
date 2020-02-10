package com.hyit.www.controller.shopadmin;

import com.hyit.www.dto.Result;
import com.hyit.www.entity.ProductCategory;
import com.hyit.www.entity.Shop;
import com.hyit.www.enums.ProductCategoryStateEnum;
import com.hyit.www.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author LiJun
 * @Date 2020/2/10 13:09
 */
@RestController
@RequestMapping(value = "/shopadmin")
public class ProductCategoryManagementController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 通过shopId获取商品信息
     */
    @GetMapping(value = "/getProductCategoryList")
    public Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        List<ProductCategory> productCategoryList;
        ProductCategoryStateEnum ps;
        // 在进入到shop管理页面（即调用getShopManageInfo方法时）,如果shopId合法，便将该shop信息放在了session中，key为currentShop
        // 这里我们不依赖前端的传入，因为不安全。 我们在后端通过session来做
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        if (currentShop != null && currentShop.getShopId() != null) {
            try {
                productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
                return new Result<>(true, productCategoryList);
            } catch (Exception e) {
                e.printStackTrace();
                ps = ProductCategoryStateEnum.EDIT_ERROR;
                return new Result<>(false, ps.getState(), ps.getStateInfo());
            }
        } else {
            ps = ProductCategoryStateEnum.NULL_SHOP;
            return new Result<>(false, ps.getState(), ps.getStateInfo());
        }
    }
}
