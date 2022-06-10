package com.example.shop.simple.exception;

import com.example.shop.simple.pojo.enums.HttpStatus;

/**
 * Author: kevin
 * Date: 2022/6/10
 * Description:
 */
public class ShopBindException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = -4137688758944857209L;

    /**
     * http状态码
     */
    private Integer httpStatusCode;

    private Object object;


    /**
     * @param httpStatus http状态码
     */
    public ShopBindException(HttpStatus httpStatus) {
        super(httpStatus.getMsg());
        this.httpStatusCode = httpStatus.value();
    }

    /**
     * @param httpStatus http状态码
     */
    public ShopBindException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatusCode = httpStatus.value();
    }


    public ShopBindException(String msg) {
        super(msg);
        this.httpStatusCode = org.springframework.http.HttpStatus.BAD_REQUEST.value();
    }

    public ShopBindException(String msg, Object object) {
        super(msg);
        this.httpStatusCode = org.springframework.http.HttpStatus.BAD_REQUEST.value();
        this.object = object;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

}
