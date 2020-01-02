package com.hyit.www.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
    /**获取项目classpath下路径*/
    private static String bastPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();

    public static String generateThumbnail(MultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        /**创建文件夹*/
        makeDirPath(targetAddr);

        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(thumbnail.getInputStream()).
                    size(200,200).
                    watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(bastPath + "guagua.jpg")),0.5f).
                    outputQuality(0.8f).toFile(dest);
        }
        catch (IOException e){
            e.printStackTrace();
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
//    public static void main(String[] args) throws IOException {
//        Thumbnails.of(new File("C:\\Users\\Administrator\\Desktop\\yangmi.jpg")).
//                size(200,200).
//                watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(bastPath + "/wechat.jpg")),0.25f).
//                outputQuality(0.8f).toFile("C:\\Users\\Administrator\\Desktop\\yangmi2.jpg");
//    }
}
