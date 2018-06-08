package com.laiyy.springquartz.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author laiyy
 * @date 2018/6/8 11:04
 * @description
 */
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.laiyy.springquartz"})
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("com.laiyy.springquartz.controller"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring-Quartz 定时任务调度")
                .version("1.0").build();
    }

}
