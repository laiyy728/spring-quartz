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

    public static AjaxDto success(String uri){
        return success(new ArrayList<>(), uri);
    }

    public static AjaxDto success(final Object obj, long count, String uri){
        AjaxDto ajaxDto = new AjaxDto();
        ajaxDto.setMsg("成功");
        ajaxDto.setCode(GlobalConstant.SUCCESS);
        ajaxDto.setData(obj);
        ajaxDto.setCount(count);
        ajaxDto.setUri(uri);
        return ajaxDto;
    }

    public static AjaxDto success(final Object obj, String uri) {
        return success(obj, 0, uri);
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
