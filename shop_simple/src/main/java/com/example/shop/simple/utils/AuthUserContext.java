package com.example.shop.simple.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.example.shop.simple.pojo.bo.UserInfoInTokenBO;

/**
 * Author: kevin
 * Date: 2022/6/10
 * Description:
 */
public class AuthUserContext {

    private static final ThreadLocal<UserInfoInTokenBO> USER_INFO_IN_TOKEN_HOLDER = new TransmittableThreadLocal<>();

    public static UserInfoInTokenBO get() {
        return USER_INFO_IN_TOKEN_HOLDER.get();
    }

    public static void set(UserInfoInTokenBO userInfoInTokenBo) {
        USER_INFO_IN_TOKEN_HOLDER.set(userInfoInTokenBo);
    }

    public static void clean() {
        if (USER_INFO_IN_TOKEN_HOLDER.get() != null) {
            USER_INFO_IN_TOKEN_HOLDER.remove();
        }
    }
}
