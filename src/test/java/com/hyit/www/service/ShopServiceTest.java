package com.hyit.www.service;

import com.hyit.www.BaseTest;
import com.hyit.www.dto.ShopExecution;
import com.hyit.www.entity.Area;
import com.hyit.www.entity.PersonInfo;
import com.hyit.www.entity.Shop;
import com.hyit.www.entity.ShopCategory;
import com.hyit.www.enums.ShopStateEnum;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() throws IOException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shop.setShopId(1L);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);
        shop.setShopName("测试的店铺1");
        shop.setShopDesc("test1");
        shop.setAdvice("审核中");
        shop.setPhone("110");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setShopAddr("test1");

        String filePath = "C:\\Users\\Administrator\\Desktop\\Gproject\\images\\yangmi.jpg";
        ShopExecution se = shopService.addShop(shop, path2MultipartFile(filePath));
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
        System.out.println("ShopExecution.state " + se.getState());
        System.out.println("ShopExecution.stateInfo " + se.getStateInfo());
    }
    /**
     * filePath to MultipartFile
     */
    private MultipartFile path2MultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
                IOUtils.toByteArray(input));
        return multipartFile;
    }

    @Test
    public void testModifyShop() throws Exception{
        Shop shop = shopService.getByShopId(1L);
        shop.setShopName("迪迪的麻辣烫");
        String filePath = "C:\\Users\\Administrator\\Desktop\\Gproject\\images\\malatang.jpg";
        ShopExecution shopExecution = shopService.modifyShop(shop, path2MultipartFile(filePath));
        System.out.println("新图片地址 " + shopExecution.getShop().getShopImg());
    }

}
