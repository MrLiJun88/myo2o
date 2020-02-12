package com.hyit.www.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    /**获取项目classpath下路径*/
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();

    /**为上传的图片打上水印*/
    public static String generateThumbnail(MultipartFile thumbnail, String targetAddr) {
        /**获取随机文件名，防止文件重名*/
        String realFileName = getRandomFileName();
        /**获取文件扩展名*/
        String extension = getFileExtension(thumbnail);
        /**创建文件夹*/
        makeDirPath(targetAddr);

        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(thumbnail.getInputStream()).
                    size(200,200).
                    watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "watermark.jpg")),0.5f).
                    outputQuality(0.8f).toFile(dest);
        }
        catch (IOException e){
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒+5位随机数
     */
    public static String getRandomFileName() {
        /**获取随机五位数*/
        int ranNum = random.nextInt(89999) + 10000;
        String newTime = sDateFormat.format(new Date());
        return newTime + ranNum;
    }

    /**
     * 获取输入文件扩展名
     */
    public static String getFileExtension(MultipartFile  thumbnail) {
        String originalFileName = thumbnail.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 创建目录路径所涉及到的目录，即/home/work/lijun/xxx.jpg,那么home work lijun
     * 这三个文件夹都得自动创建
     */
    public static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if(! dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * 当商铺图片更新时，删除旧的图片和文件夹
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        // 存在
        if (fileOrPath.exists()) {
            // 如果是目录，则递归删除
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            // 删除文件或文件夹,则直接删除
            fileOrPath.delete();
        }
    }

    /**
     * 处理商品缩略图
     * @param thumbnail  Spring自带的文件处理对象
     * @param targetAddr 图片存储路径
     * @return
     */
    public static String generateProductImg(MultipartFile thumbnail, String targetAddr) {
        // 获取随机文件名，防止文件重名
        String realFileName = getRandomFileName();
        // 获取文件扩展名
        String extension = getFileExtension(thumbnail);
        // 在文件夹不存在时创建
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "watermark.jpg")), 0.5f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    /**
     * filePath to MultipartFile
     *
     * @param filePath
     * @throws IOException
     */
    public static MultipartFile path2MultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
                IOUtils.toByteArray(input));
        return multipartFile;
    }

    /**
     * 处理商品分类图
     * @param thumbnail  Spring自带的文件处理对象
     * @param targetAddr 图片存储路径
     */
    public static String generateShopCategoryImg(MultipartFile thumbnail, String targetAddr) {
        // 获取随机文件名，防止文件重名
        String realFileName = getRandomFileName();
        // 获取文件扩展名
        String extension = getFileExtension(thumbnail);
        // 在文件夹不存在时创建
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(50, 50).outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    /**
     * 处理首页头图
     * @param thumbnail  Spring自带的文件处理对象
     * @param targetAddr 图片存储路径
     */
    public static String generateHeadImg(MultipartFile thumbnail, String targetAddr) {
        // 获取随机文件名，防止文件重名
        String realFileName = getRandomFileName();
        // 获取文件扩展名
        String extension = getFileExtension(thumbnail);
        // 在文件夹不存在时创建
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(400, 300).outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }
}
