package com.cloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author WXY
 * @ClassName Swagger2
 * @Description T0D0
 * @Date 2019/8/2 15:12
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * createRestApi函数创建Docket的Bean
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket( DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis( RequestHandlerSelectors.basePackage("com.cloud.controller"))
                .paths( PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("Spring Boot相关文章请关注： https://pignum1.github.io/")
                .termsOfServiceUrl(" https://pignum1.github.io/")
                .version("1.0")
                .build();
    }
}