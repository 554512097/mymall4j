package com.example.shop.simple.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.shop.simple.exception.ShopBindException;
import com.example.shop.simple.manager.PasswordManager;
import com.example.shop.simple.manager.TokenStore;
import com.example.shop.simple.pojo.bo.UserInfoInTokenBO;
import com.example.shop.simple.pojo.enums.SysTypeEnum;
import com.example.shop.simple.pojo.model.User;
import com.example.shop.simple.pojo.param.UserRegisterParam;
import com.example.shop.simple.pojo.vo.TokenInfoVO;
import com.example.shop.simple.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * Author: kevin
 * Date: 2022/6/8
 * Description:
 */
@Slf4j
@Api("用户数据接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService mUserService;
    private final PasswordManager mPasswordManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenStore mTokenStore;

    public UserController(UserService userService,
                          PasswordManager passwordManager,
                          PasswordEncoder passwordEncoder,
                          TokenStore tokenStore) {
        mUserService = userService;
        mPasswordManager = passwordManager;
        this.passwordEncoder = passwordEncoder;
        mTokenStore = tokenStore;
    }


    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "用户注册或绑定手机号接口")
    public ResponseEntity<TokenInfoVO> register(@Valid @RequestBody UserRegisterParam param) {
        if (StrUtil.isBlank(param.getNickName())) {
            param.setNickName(param.getUserName());
        }
        // 正在进行申请注册
        if (mUserService.count(new LambdaQueryWrapper<User>().eq(User::getNickName, param.getNickName())) > 0) {
            // 该用户名已注册，无法重新注册
            throw new ShopBindException("该用户名已注册，无法重新注册");
        }
        Date now = new Date();
        User user = new User();
        user.setModifyTime(now);
        user.setUserRegtime(now);
        user.setStatus(1);
        user.setNickName(param.getNickName());
        user.setUserMail(param.getUserMail());
        String decryptPassword = mPasswordManager.decryptPassword(param.getPassword());
        user.setLoginPassword(passwordEncoder.encode(decryptPassword));
        String userId = IdUtil.simpleUUID();
        user.setUserId(userId);
        mUserService.save(user);
        // 2. 登录
        UserInfoInTokenBO userInfoInTokenBO = new UserInfoInTokenBO();
        userInfoInTokenBO.setUserId(user.getUserId());
        userInfoInTokenBO.setSysType(SysTypeEnum.ORDINARY.value());
        userInfoInTokenBO.setIsAdmin(0);
        userInfoInTokenBO.setEnabled(true);
        return ResponseEntity.ok(mTokenStore.storeAndGetVo(userInfoInTokenBO));
    }
}
