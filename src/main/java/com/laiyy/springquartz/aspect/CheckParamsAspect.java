package com.laiyy.springquartz.aspect;

import com.laiyy.springquartz.annotations.CheckParams;
import com.laiyy.springquartz.exceptions.NullValueException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/21 11:04
 * @description 检查是否有空值的切面，用 @Before 在方法执行前检查
 */
@Component
@Aspect
public class CheckParamsAspect {

    @Pointcut("@annotation(com.laiyy.springquartz.annotations.CheckParams)")
    public void check(){

    }

    @Before("check()")
    public void checkParams(JoinPoint joinPoint) {
        // 获取返请求参数的值
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取请求参数的名字
        String[] parameterNames = signature.getParameterNames();
        Method method = signature.getMethod();
        CheckParams checkParams = method.getAnnotation(CheckParams.class);
        List<String> paramList = Arrays.asList(checkParams.fields());
        for (int index = 0; index < parameterNames.length; index++) {
            String paramName = parameterNames[index];
            if (paramList.contains(paramName)) {
                // 如果需要检验的参数存在，获取参数的值
                Object value = args[index];
                if (value == null) {
                    throw new NullValueException(paramName + " 不能为空");
                } else {
                    // 判断值的类型
                    if (value instanceof String) {
                        String paramValue = (String) value;
                        if (StringUtils.isBlank(paramValue)) {
                            throw new NullValueException(paramName + " 不能为空");
                        }
                    }
                }
            }
        }
    }
}
