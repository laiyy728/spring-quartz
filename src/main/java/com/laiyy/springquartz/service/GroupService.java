package com.laiyy.springquartz.service;

import com.laiyy.springquartz.base.BaseService;
import com.laiyy.springquartz.model.Group;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/21 16:24
 * @description
 */
public interface GroupService extends BaseService<Group, Integer> {

    /**
     * 修改任务组状态
     * @param status 任务组状态
     * @param id 任务组id
     */
    void updateGroupStatus(int status, int id);


    /**
     * 根据任务组状态获取任务所有任务组
     * @param status 任务组状态
     * @return 对应状态的所有任务组
     */
    List<Group> findAllByStatusOrderByCreateDateDesc(int status);

    /**
     * 根据状态获取分页数据
     * @param status 状态
     * @param page 当前页
     * @param limit 每页显示多少
     * @return 分页数据
     */
    Page<Group> findGroupByStatus(int status, int page, int limit);

    /**
     * 根据状态获取分页数据
     * @param status 状态
     * @param page 当前页
     * @param limit 每页显示多少
     * @param name 组名
     * @return 分页数据
     */
    Page<Group> findGroupByStatusAndNameLike(int status, String name, int page, int limit);

}
