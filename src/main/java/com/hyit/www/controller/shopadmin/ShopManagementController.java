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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/registerShop",method = RequestMethod.POST)
    @ResponseBody
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
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            /**向数据库中添加店铺信息*/
            ShopExecution shopExecution = shopService.addShop(shop,shopImg);
            if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                map.put("success",true);
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
    @ResponseBody
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
}

