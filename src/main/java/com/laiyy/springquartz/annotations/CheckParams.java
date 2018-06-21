package com.laiyy.springquartz.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author laiyy
 * @date 2018/6/21 11:01
 * @description 自定义注解：检查是 fields 中是否有空值
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckParams {

    String[] fields() default {};

    String message() default "";

}
