package com.pinyougou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Component
@ComponentScan(basePackages = "com.pinyougou.manager.controller")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("品优购电商后台系统")
                .description("后台系统接口测试")
                .version("1.0")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .build();
    }
}
