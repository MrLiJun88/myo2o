package com.hyit.www.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyit.www.dto.ProductExecution;
import com.hyit.www.entity.Product;
import com.hyit.www.entity.Shop;
import com.hyit.www.enums.OperationStatusEnum;
import com.hyit.www.enums.ProductStateEnum;
import com.hyit.www.exceptions.ProductOperationException;
import com.hyit.www.service.ProductService;
import com.hyit.www.util.CodeUtil;
import com.hyit.www.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author LiJun
 * @Date 2020/2/11 13:29
 */

@RestController
@RequestMapping(value = "/shopadmin")
public class ProductManagementController {

    @Autowired
    private ProductService productService;

    /**
     *  支持上传商品详情图的最大数量
     */
    private static final int IMAGE_MAX_COUNT = 6;

    /**
     * 添加商品
     * 1. 验证码校验
     * 2. 接收前端参数：包括 商品、 商品缩略图、商品详情图片实体类
     * 前端页面通过post方式传递一个包含文件上传的Form会以multipart/form-data请求发送给服务器，
     * 需要告诉DispatcherServlet如何处理MultipartRequest，我们在spring-web.xml中定义了multipartResolver。
     *
     * 如果某个Request是一个MultipartRequest，它就会首先被MultipartResolver处理， 然后再转发相应的Controller。
     * 在Controller中，
     * 将HttpServletRequest转型为MultipartHttpServletRequest，可以非常方便的得到文件名和文件内容
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/addProduct")
    public Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(16);
        // Step1:校验验证码
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", OperationStatusEnum.VERIFYCODE_ERROR.getStateInfo());
            return modelMap;
        }
        // Step2: 使用FastJson提供的api,实例化Product 构造调用service层的第一个参数
        String productStr = null;
        Product product = null;
        // 将json转换为pojo
        ObjectMapper mapper = new ObjectMapper();
        try {
            productStr = HttpServletRequestUtil.getString(request, "productStr");
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        // Step3: 商品缩略图 和 商品详情图 构造调用service层的第二个参数和第三个参数
        // 图片缩略图
        MultipartFile productImg = null;
        // 商品详情图
        List<MultipartFile> productDetailImgList = new ArrayList<>(16);
        try {
            // 创建一个通用的多部分解析器
            MultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {
                productImg = handleImage(request, productDetailImgList);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", OperationStatusEnum.PIC_EMPTY.getStateInfo());
                return modelMap;
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        // Step4 调用Service层
        if (product != null && productImg != null && productDetailImgList.size() > 0) {
            try {
                // 从session中获取shop信息，不依赖前端的传递更加安全
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                // 调用addProduct
                ProductExecution pe = productService.addProduct(product, productImg, productDetailImgList);
                if (pe.getState() == OperationStatusEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (ProductOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", ProductStateEnum.PRODUCT_EMPTY.getStateInfo());
        }
        return modelMap;
    }

    /**
     * 处理图片私有方法
     * @param request
     * @param productDetailImgList
     * @return
     */
    private MultipartFile handleImage(HttpServletRequest request, List<MultipartFile> productDetailImgList) {
        MultipartHttpServletRequest multipartRequest;
        MultipartFile productImg;
        // 与前端约定使用productImg，得到商品缩略图
        multipartRequest = (MultipartHttpServletRequest) request;
        productImg = (MultipartFile) multipartRequest.getFile("productImg");

        // 得到商品详情的列表，和前端约定使用productDetailImg + i 传递
        for (int i = 0; i < IMAGE_MAX_COUNT; i++) {
            MultipartFile productDetailImg = (MultipartFile) multipartRequest.getFile("productDetailImg" + i);
            if (productDetailImg != null) {
                productDetailImgList.add(productDetailImg);
            } else {
                // 如果从请求中获取的到file为空，终止循环
                break;
            }
        }
        return productImg;
    }
}
