package com.hyit.www.util;

/**
 * @Author LiJun
 * @Date 2020/2/17 18:25
 * @Description: 继承PropertyPlaceholderConfigurer，重写convertProperty
 */
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    /**需要加密的字段数组*/
    private String[] encryptPropNames = { "database.username", "database.password", "redis.hostname" };

    /**
     * 对关键的属性进行转换
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        // 判断是否加密
        if (isEncryptProp(propertyName)) {
            // 解密
            String decryptValue = DESUtil.getDecryptString(propertyValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    /**
     * 判断该属性是否加密
     * @param propertyName
     */
    private boolean isEncryptProp(String propertyName) {
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName)) {
                return true;
            }
        }
        return false;
    }
}
