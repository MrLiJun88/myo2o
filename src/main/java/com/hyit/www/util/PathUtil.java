package com.hyit.www.util;

public class PathUtil {

    /**获取系统文件的分隔符*/
    private static String separator = System.getProperty("file.separator");

    /**获取项目图片的根路径*/
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "C:/Users/Administrator/Desktop/Gproject/images/";
        }
        else {
            basePath = "/home/lijun/images";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }
    /**获取商铺对应的图片路径*/
    public static String getShopImagePath(long shopId) {
        String imagePath = "upload/item/shop" + shopId + "/";
        return imagePath.replace("/",separator);
    }
}
