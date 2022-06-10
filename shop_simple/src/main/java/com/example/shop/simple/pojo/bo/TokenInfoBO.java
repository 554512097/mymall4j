package com.example.shop.simple.pojo.bo;

import lombok.Data;

/**
 * Author: kevin
 * Date: 2022/6/10
 * Description:
 */
@Data
public class TokenInfoBO {

    /**
     * 保存在token信息里面的用户信息
     */
    private UserInfoInTokenBO userInfoInToken;

    private String accessToken;

    private String refreshToken;

    /**
     * 在多少秒后过期
     */
    private Integer expiresIn;
}
