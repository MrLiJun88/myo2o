package com.hyit.www.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyit.www.dto.ShopExecution;
import com.hyit.www.entity.Area;
import com.hyit.www.entity.PersonInfo;
import com.hyit.www.entity.Shop;
import com.hyit.www.entity.ShopCategory;
import com.hyit.www.enums.ShopStateEnum;
import com.hyit.www.service.AreaService;
import com.hyit.www.service.ShopCategoryService;
import com.hyit.www.service.ShopService;
import com.hyit.www.util.CodeUtil;
import com.hyit.www.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    /**
     * 注册新店铺
     */
    @RequestMapping(value = "/registerShop",method = RequestMethod.POST)
    private Map<String,Object> registerShop(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>(16);
        /**检查输入的验证码是否正确*/
        if(! CodeUtil.checkVerifyCode(request)){
            map.put("success",false);
            map.put("errMsg","输入的验证码错误");
            return map;
        }
        /**1.接收页面传送的参数并转化成实体对象，包含店铺信息以及图片信息*/
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try{
            /**将json类型转成在实例对象*/
            shop = mapper.readValue(shopStr,Shop.class);
        }
        catch (Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
            return map;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            /**获取页面上传的图片文件*/
            shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
        }
        else {
            map.put("success",false);
            map.put("errMsg","上传图片不能为空");
            return map;
        }
        /**2.注册店铺*/
        if (null != shop && null != shopImg) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
            /**向数据库中添加店铺信息*/
            ShopExecution shopExecution = shopService.addShop(shop,shopImg);
            if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                map.put("success",true);
                /**该用户可以操作的店铺列表*/
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if(null == shopList || shopList.size() == 0){
                    shopList = new ArrayList<>(16);
                }
                shopList.add(shopExecution.getShop());
                request.getSession().setAttribute("shopList",shopList);
            }
            else {
                map.put("success", false);
                map.put("errMsg", shopExecution.getStateInfo());
            }
            return map;
        } else {
            map.put("success", false);
            map.put("errMsg", "请输入店铺信息");
            return map;
        }
    }

    /**
     * 获取区域与店铺类型信息，并返回给前台页面
     */
    @RequestMapping(value = "/getShopInitInfo",method = RequestMethod.GET)
    public Map<String, Object> getShopInitInfo() {
        Map<String,Object> modelMap = new HashMap<>(16);
        List<ShopCategory> shopCategoryList;
        List<Area> areaList;
        try{
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }
        catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        return modelMap;
    }

    /**
     * 根据shopId获取到店铺信息
     */
    @GetMapping(value = "/getShopById")
    public Map<String,Object> getShopById(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        // 获取shopId
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        System.out.println("shopId " + shopId);
        if (shopId > -1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                map.put("shop", shop);
                // 获取区域列表
                List<Area> areaList = areaService.getAreaList();
                map.put("areaList", areaList);
                map.put("success", true);
            } catch (Exception e) {
                map.put("success", false);
                map.put("errMsg", e.toString());
            }
        } else {
            map.put("success", false);
            map.put("errMsg", ShopStateEnum.NULL_SHOPID.getStateInfo());
        }
        return map;
    }

    /**
     * 更新店铺信息
     */
    @PostMapping(value = "/modifyShop")
    public Map<String,Object> modifyShop(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>(16);
        /**检查输入的验证码是否正确*/
        if(! CodeUtil.checkVerifyCode(request)){
            map.put("success",false);
            map.put("errMsg","输入的验证码错误");
            return map;
        }
        /**1.接收页面传送的参数并转化成实体对象，包含店铺信息以及图片信息*/
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try{
            /**将json类型转成在实例对象*/
            shop = mapper.readValue(shopStr,Shop.class);
        }
        catch (Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
            return map;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            /**获取页面上传的图片文件*/
            shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");
        }
        /**2.修改店铺信息*/
        if (null != shop && null != shop.getShopId()) {
            ShopExecution shopExecution = null;
            /**向数据库中添加店铺信息*/
            if(null == shopImg){
                //说明没有更新图片
                shopExecution = shopService.modifyShop(shop,null);
            }
            else {
                shopExecution = shopService.modifyShop(shop,shopImg);
            }
            if (shopExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
                map.put("success",true);
            }
            else {
                map.put("success", false);
                map.put("errMsg", shopExecution.getStateInfo());
            }
            return map;
        } else {
            map.put("success", false);
            map.put("errMsg", "请输入店铺id");
            return map;
        }
    }

    /**
     * 根据模糊查询的条件，获取符合条件的店铺列表集合
     */
    @GetMapping(value = "/getShopList")
    public Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(16);
        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("李俊");
        request.getSession().setAttribute("user",user);
        user = (PersonInfo) request.getSession().getAttribute("user");
        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwner(user);
            ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 50);
            map.put("shopList", shopExecution.getShopList());
            // 列出店铺成功之后，将店铺放入session中作为权限验证依据，即该帐号只能操作它自己的店铺
            request.getSession().setAttribute("shopList", shopExecution.getShopList());
            map.put("user", user);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }

    /**
     * 获取店铺管理信息
     */
    @GetMapping(value = "/getShopManagementInfo")
    public Map<String,Object> getShopManagementInfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>(16);
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        // 如果shopId不存在
        if (shopId <= 0) {
            // 从session中获取店铺信息
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null) {
                // 如果session中没有店铺信息，视为非法操作，重定向回店铺列表页面
                map.put("redirect", true);
                map.put("url", "/myo2o/shopadmin/shoplist");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                map.put("redirect", false);
                map.put("shopId", currentShop.getShopId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            map.put("redirect", false);
        }
        return map;
    }
}

