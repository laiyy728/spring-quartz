package com.laiyy.springquartz.util;

import com.laiyy.springquartz.constants.GlobalConstant;
import com.laiyy.springquartz.dto.AjaxDto;

import java.util.ArrayList;

/**
 * @author laiyy
 * @date 2018/6/21 10:52
 * @description 通用返回值方法
 */
public class ResultUtils {

    public static AjaxDto success(final Object obj, long count){
        AjaxDto ajaxDto = new AjaxDto();
        ajaxDto.setMsg("成功");
        ajaxDto.setCode(GlobalConstant.SUCCESS);
        ajaxDto.setData(obj);
        ajaxDto.setCount(count);
        return ajaxDto;
    }

    public static AjaxDto succee(final Object obj) {
        return success(obj, 0);
    }

    public static AjaxDto fail(final String msg) {
        AjaxDto ajaxDto = new AjaxDto();
        ajaxDto.setMsg(msg);
        ajaxDto.setData(new ArrayList<>());
        ajaxDto.setCode(GlobalConstant.FAIL);
        ajaxDto.setCount(0);
        return ajaxDto;
    }

}
