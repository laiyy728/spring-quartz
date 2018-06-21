package com.laiyy.springquartz.aspect;

import com.alibaba.fastjson.JSON;
import com.laiyy.springquartz.util.ResultUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/21 13:48
 * @description
 */
@Component
@Aspect
public class ReturnValueAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void returnValue() {

    }

    @AfterReturning(pointcut = "returnValue()", returning = "value")
    public void returnValue(Object value) {
        String json;
        if (value instanceof Page) {
            Page page = (Page) value;
            List data = page.getContent();
            long count = page.getTotalElements();
            json = JSON.toJSONString(ResultUtils.success(data, count));
        } else {
            json = JSON.toJSONString(ResultUtils.succee(value));
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
            if (response != null) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                ServletOutputStream outputStream = null;
                try {
                    outputStream = response.getOutputStream();
                    outputStream.write(json.getBytes("UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.flush();
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}