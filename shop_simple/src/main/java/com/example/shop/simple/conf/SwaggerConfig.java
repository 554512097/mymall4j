package com.example.shop.simple.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger文档，只有在测试环境才会使用
 */
@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket baseRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("基础版")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.shop.simple.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mall4j商城接口文档")
                .description("mall4j商城接口文档Swagger版")
                .termsOfServiceUrl("http://www.mall4j.com/")
                .contact(new Contact("Kevin", "https://www.mall4j.com/", "554512097@qq.com"))
                .version("1.0")
                .build();
    }
}