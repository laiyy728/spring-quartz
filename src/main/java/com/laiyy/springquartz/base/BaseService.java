package com.laiyy.springquartz.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/8 11:19
 * @description 通用 Service
 */
public interface BaseService<T, ID extends Serializable> extends Service {

    /**
     * 删除一个实体
     * @param t 需要删除的实体
     */
    void delete(T t);

    /**
     * 根据id删除一个实体
     * @param id 需要删除的 id
     */
    void delete(ID id);

    /**
     * 根据 id 获取信息
     * @param id 需要获取信息的 id
     * @return 获取到的实体
     */
    T get(ID id);

    /**
     * 获取全部信息
     * @return 获取到的实体列表
     */
    List<T> findAll();

    /**
     * 获取分页信息
     * @param pageable 分页参数
     * @return 分页消息
     */
    Page<T> findByPageable(Pageable pageable);

    /**
     * 添加实体，并返回实体
     * @param t 添加的实体
     * @return 实体 id
     */
    T add(T t);

    /**
     * 修改实体信息
     * @param t 修改的实体信息
     * @return 修改后的实体信息
     */
    T update(T t);

}
