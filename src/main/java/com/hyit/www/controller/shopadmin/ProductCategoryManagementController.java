package com.hyit.www.controller.shopadmin;

import com.hyit.www.dto.ProductCategoryExecution;
import com.hyit.www.dto.Result;
import com.hyit.www.entity.ProductCategory;
import com.hyit.www.entity.Shop;
import com.hyit.www.enums.OperationStatusEnum;
import com.hyit.www.enums.ProductCategoryStateEnum;
import com.hyit.www.exceptions.ProductCategoryOperationException;
import com.hyit.www.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 批量添加产品类型信息
     */
    @PostMapping(value = "/addProductCategorys")
    public Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,
                                                   HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // 列表不为空
        if (productCategoryList != null && !productCategoryList.isEmpty()) {
            // 从session中获取店铺信息，尽量减少对前端的依赖
            Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
            if (currentShop != null && currentShop.getShopId() != null) {
                for (ProductCategory productCategory : productCategoryList) {
                    productCategory.setShopId(currentShop.getShopId());
                    productCategory.setCreateTime(new Date());
                }
            }
            try {
                // 批量插入
                ProductCategoryExecution productCategoryExecution = productCategoryService
                        .batchAddProductCategory(productCategoryList);
                if (productCategoryExecution.getState() == OperationStatusEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                    // 同时也将新增成功的数量返回给前台
                    modelMap.put("effectNum", productCategoryExecution.getCount());
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", productCategoryExecution.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", ProductCategoryStateEnum.EMPETY_LIST.getStateInfo());
        }
        return modelMap;
    }

    /**
     * 删除指定的商品类别信息
     * @param productCategoryId
     * @param request
     */
    @RequestMapping(value = "/removeProductCategory", method = RequestMethod.POST)
    public Map<String, Object> removeProductCategory(Long productCategoryId, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        if (productCategoryId != null && productCategoryId > 0) {
            // 从session中获取shop的信息
            Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
            if (currentShop != null && currentShop.getShopId() != null) {
                try {
                    // 删除
                    Long shopId = currentShop.getShopId();
                    ProductCategoryExecution pce = productCategoryService.deleteProductCategory(productCategoryId,
                            shopId);
                    if (pce.getState() == OperationStatusEnum.SUCCESS.getState()) {
                        modelMap.put("success", true);
                    } else {
                        modelMap.put("success", false);
                        modelMap.put("errMsg", pce.getStateInfo());
                    }
                } catch (ProductCategoryOperationException e) {
                    e.printStackTrace();
                    modelMap.put("success", false);
                    modelMap.put("errMsg", e.getMessage());
                    return modelMap;
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", ProductCategoryStateEnum.NULL_SHOP.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", ProductCategoryStateEnum.EMPETY_LIST.getStateInfo());
        }
        return modelMap;
    }
}
