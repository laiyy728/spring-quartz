//package com.laiyy.springquartz.conf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @author laiyy
// * @date 2018/6/8 11:04
// * @description
// *
// * SpringBoot 与 Swagger 整合时，踩到的坑。
// *
// * 与 xml 配置 Spring 项目不同的是，SpringBoot 中不用使用 @EnableWebMvc
// * 如果在 SwaggerConfig 上加上  @EnableWebMvc 注解，会导致 /resources/** 静态资源无法访问
// *
// * 原因在于：@EnableWebMvc = WebConfigurationSupport，使用了 @EnableWebMvc 注解等于扩展了 WebConfigurationSupport，但是没有重写任何方法，
// * 会屏蔽到 SpringBoot 中 WebConfigurationSupport 的所有设置。即：会屏蔽掉所有的 mvc 配置，需要手动重写。
// * 而不使用 @EnableWebMvc，可以使用 SpringBoot 默认的 mvc 配置，且不会影响后续操作。
// */
////@EnableSwagger2
////@ComponentScan(basePackages = {"com.laiyy.springquartz"})
////@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Docket createRestApi(){
//        return  new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .select().apis(RequestHandlerSelectors.basePackage("com.laiyy.springquartz.controller"))
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any()).build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("Spring-Quartz 定时任务调度")
//                .version("1.0").build();
//    }
//
//}
