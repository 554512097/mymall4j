package com.example.shop.simple.manager;

import cn.hutool.crypto.symmetric.AES;
import com.example.shop.simple.exception.ShopBindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Author: kevin
 * Date: 2022/6/10
 * Description:
 */
@Slf4j
@Component
public class PasswordManager {

    /**
     * 用于aes签名的key，16位
     */
    @Value("${mall.password.sign-key}")
    public String passwordSignKey;

    public String decryptPassword(String data) {
        AES aes = new AES(passwordSignKey.getBytes(StandardCharsets.UTF_8));
        String decryptStr;
        String decryptPassword;
        try {
            decryptStr = aes.decryptStr(data);
            decryptPassword = decryptStr.substring(13);
        } catch (Exception e) {
            log.error("Exception:", e);
            throw new ShopBindException("AES解密错误", e);
        }
        return decryptPassword;
    }
}
