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

    /**
     * 分页信息工具
     * @param page 当前页码
     * @return 分页信息
     */
    public static PageRequest of(int page) {
        return PageRequest.of(page <= 1 ? 0 : page - 1, GlobalConstant.PAGE_SIZE);
    }

    /**
     * 分页信息工具
     * @param page 当前页码
     * @param size 每页显示条数
     * @return 分页信息
     */
    public static PageRequest of(int page, int size) {
        if (size == 0) {
            return of(page);
        }
        return PageRequest.of(page <= 1 ? 0 : page - 1, size);
    }

    /**
     * 分页信息工具
     * @param page 当前页码
     * @param sort 排序方式
     * @param field 排序字段
     * @return 分页信息
     */
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

    /**
     * 分页信息工具
     * @param page 当前页码
     * @param limit 每页显示条数
     * @param sortEnum 排序方式
     * @param field 排序字段
     * @return 分页信息
     */
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
