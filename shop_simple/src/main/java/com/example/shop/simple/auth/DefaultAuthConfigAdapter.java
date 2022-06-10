package com.example.shop.simple.auth;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * Author: kevin
 * Date: 2022/6/9
 * Description:
 */
@Slf4j
public class DefaultAuthConfigAdapter implements AuthConfigAdapter {

    @Override
    public List<String> pathPatterns() {
        return Collections.singletonList("/*");
    }

    @Override
    public List<String> excludePathPatterns() {
        return Collections.singletonList("/swagger-ui");
    }
}
