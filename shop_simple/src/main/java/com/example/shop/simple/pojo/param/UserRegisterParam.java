package com.example.shop.simple.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Author: kevin
 * Date: 2022/6/8
 * Description:
 */
@Data
@ApiModel("设置用户信息")
public class UserRegisterParam {
    @ApiModelProperty("用户名")
    private String userName;

    @Length(min = 8, message = "密码长度不能小于8位")
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String userMail;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "头像")
    private String img;

    @ApiModelProperty(value = "校验登陆注册验证码成功的标识")
    private String checkRegisterSmsFlag;

    @ApiModelProperty(value = "当账户未绑定时，临时的uid")
    private String tempUid;

    @ApiModelProperty(value = "用户id")
    private Long userId;
}
