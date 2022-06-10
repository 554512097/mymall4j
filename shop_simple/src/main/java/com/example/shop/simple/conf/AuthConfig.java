package com.example.shop.simple.conf;

import com.example.shop.simple.auth.AuthConfigAdapter;
import com.example.shop.simple.auth.DefaultAuthConfigAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: kevin
 * Date: 2022/6/9
 * Description:
 */
@Configuration
public class AuthConfig {
    @Bean
    @ConditionalOnMissingBean
    public AuthConfigAdapter authConfigAdapter() {
        return new DefaultAuthConfigAdapter();
    }
}
