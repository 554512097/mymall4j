package com.example.shop.simple.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shop.simple.pojo.model.User;
import com.example.shop.simple.pojo.param.UserRegisterParam;

/**
 * Author: kevin
 * Date: 2022/6/8
 * Description:
 */
public interface UserService extends IService<User> {

    User getUserByUserId(String userId);

    void validate(UserRegisterParam userRegisterParam, String checkRegisterSmsFlag);
}
