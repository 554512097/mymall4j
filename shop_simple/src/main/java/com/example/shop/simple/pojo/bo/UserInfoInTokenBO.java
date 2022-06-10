package com.example.shop.simple.pojo.bo;

import com.example.shop.simple.pojo.enums.SysTypeEnum;
import lombok.Data;

import java.util.Set;

/**
 * Author: kevin
 * Date: 2022/6/9
 * Description:
 */
@Data
public class UserInfoInTokenBO {

    /**
     * 用户在自己系统的用户id
     */
    private String userId;

    /**
     * 租户id (商家id)
     */
    private Long shopId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 系统类型
     * @see SysTypeEnum
     */
    private Integer sysType;

    /**
     * 是否是管理员
     */
    private Integer isAdmin;

    private String bizUserId;

    /**
     * 权限列表
     */
    private Set<String> perms;

    /**
     * 状态 1 正常 0 无效
     */
    private Boolean enabled;

    /**
     * 其他Id
     */
    private Long otherId;

}