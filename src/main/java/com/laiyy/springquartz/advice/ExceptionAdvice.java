package com.laiyy.springquartz.advice;

import com.laiyy.springquartz.dto.AjaxDto;
import com.laiyy.springquartz.exceptions.NullValueException;
import com.laiyy.springquartz.util.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author laiyy
 * @date 2018/6/21 10:51
 * @description
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public AjaxDto nullValueException(ConstraintViolationException exception){
        String message = "";
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            message = constraintViolation.getMessage();
        }
        if (StringUtils.isBlank(message)) {
            message = exception.getMessage();
        }
        return ResultUtils.fail(message);
    }

    @ExceptionHandler(NullValueException.class)
    @ResponseBody
    public AjaxDto nullValueException(NullValueException e){
        String message = e.getLocalizedMessage();
        logger.debug(">>>>>>>>>>>>>>>>>>{} <<<<<<<<<<<<<<<<", message);
        return ResultUtils.fail(message);
    }

}