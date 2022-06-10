package com.example.shop.simple.auth;

import java.util.List;

/**
 * Author: kevin
 * Date: 2022/6/9
 * Description:
 */
public interface AuthConfigAdapter {
    /**
     * 也许需要登录才可用的url
     */
    String MAYBE_AUTH_URI = "/**/ma/**";
    /**
     * 需要授权登陆的路径
     * @return 需要授权登陆的路径列表
     */
    List<String> pathPatterns();

    /**
     * 不需要授权登陆的路径
     * @return 不需要授权登陆的路径列表
     */
    List<String> excludePathPatterns();
}
