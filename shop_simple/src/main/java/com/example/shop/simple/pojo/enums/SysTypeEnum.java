package com.example.shop.simple.pojo.enums;

/**
 * Author: kevin
 * Date: 2022/6/9
 * Description:
 */
public enum SysTypeEnum {
    /**
     * 普通用户系统
     */
    ORDINARY(0),

    /**
     * 后台
     */
    ADMIN(1),
    ;

    private final Integer value;

    public Integer value() {
        return value;
    }

    SysTypeEnum(Integer value) {
        this.value = value;
    }
}
