package com.laiyy.springquartz.aspect;

import com.alibaba.fastjson.JSON;
import com.laiyy.springquartz.util.ResultUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/21 13:48
 * @description 返回值处理切面
 */
@Component
@Aspect
public class ReturnValueAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void returnValue() {

    }

    @AfterReturning(pointcut = "returnValue()", returning = "value")
    public void returnValue(JoinPoint point, Object value) {

        String url = getUrl(point);

        String json;
        if (value instanceof Void) {
            json = JSON.toJSONString(ResultUtils.success(url));
        } else if (value instanceof Page) {
            // 如果返回是 Page 类型
            Page page = (Page) value;
            List data = page.getContent();
            long count = page.getTotalElements();
            json = JSON.toJSONString(ResultUtils.success(data, count, url));
        } else {
            json = JSON.toJSONString(ResultUtils.success(value, url));
        }
        write(json);
    }

    private void write(String json) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
            if (response != null) {
                // 封装后输出
                response.setCharacterEncoding(Charset.defaultCharset().name());
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

    private String getUrl(JoinPoint point) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

            StringBuilder url = new StringBuilder(request.getRequestURL().toString());
            MethodSignature signature = (MethodSignature) point.getSignature();
            Annotation[] annotations = signature.getMethod().getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof GetMapping) {
                    // 如果是 get mapping
                    Object[] args = point.getArgs();
                    String[] parameterNames = signature.getParameterNames();
                    for (int index = 0; index < parameterNames.length; index++) {
                        if (args[index] != null) {
                            url.append(index == 0 ? "?" : "&").append(parameterNames[index]).append("=").append(args[index]);
                        }
                    }
                }
            }
            return url.toString();
        }
        return "";
    }
}
