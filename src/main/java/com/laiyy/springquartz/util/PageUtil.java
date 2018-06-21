package com.laiyy.springquartz.util;

import com.laiyy.springquartz.constants.GlobalConstant;
import com.laiyy.springquartz.enums.SortEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author laiyy
 * @date 2018/6/8 11:43
 * @description 分页信息
 */
public class PageUtil {

    public static PageRequest of(int page) {
        return PageRequest.of(page <= 1 ? 0 : page - 1, GlobalConstant.PAGE_SIZE);
    }

    public static PageRequest of(int page, int size) {
        if (size == 0) {
            return of(page);
        }
        return PageRequest.of(page <= 1 ? 0 : page - 1, size);
    }

    public static PageRequest of(int page, SortEnum sort, String... field) {
        if (field == null || field.length == 0) {
            return of(page);
        }
        switch (sort) {
            case ASC:
                return PageRequest.of(page <= 1 ? 0 : page - 1,
                        GlobalConstant.PAGE_SIZE,
                        Sort.by(Sort.Direction.ASC, field));
            case DESC:
                return PageRequest.of(page <= 1 ? 0 : page - 1,
                        GlobalConstant.PAGE_SIZE,
                        Sort.by(Sort.Direction.DESC, field));
            default:
                return of(page);
        }
    }

    public static PageRequest of(int page, int limit, SortEnum sortEnum, String... field) {
        if (field == null || field.length == 0) {
            return of(page, limit);
        }
        switch (sortEnum) {
            case DESC:
                return PageRequest.of(page <= 1 ? 0 : page - 1,
                        limit,
                        Sort.by(Sort.Direction.DESC, field));
            case ASC:
                return PageRequest.of(page <= 1 ? 0 : page - 1,
                        limit,
                        Sort.by(Sort.Direction.ASC, field));
            default:
                return of(page, limit);
        }
    }

}
