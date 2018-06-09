package com.laiyy.springquartz.util;

import com.laiyy.springquartz.constants.GlobalConstant;
import org.springframework.data.domain.PageRequest;

/**
 * @author laiyy
 * @date 2018/6/8 11:43
 * @description 分页信息
 */
public class PageUtil {

    public static PageRequest of(int page){
        return PageRequest.of( page <= 1 ? 0 : page - 1, GlobalConstant.PAGE_SIZE);
    }

    public static PageRequest of(int page, int size) {
        if (size == 0) {
            return of(page);
        }
        return PageRequest.of(page <= 1 ? 0 : page - 1, size);
    }

}
