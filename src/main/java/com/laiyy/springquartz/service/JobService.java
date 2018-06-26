package com.laiyy.springquartz.service;

import com.laiyy.springquartz.base.BaseService;
import com.laiyy.springquartz.model.Group;
import com.laiyy.springquartz.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/8 10:57
 * @description
 */
public interface JobService extends BaseService<Job, Integer>{

    /**
     * 添加任务
     * @param job 任务信息
     * @return 添加的任务信息
     */
    Job addJob(Job job);

    /**
     * 根据 任务 key 获取任务信息
     * @param jobKey 任务 key
     * @return 任务信息
     */
    Job findJobByJobKey(String jobKey);


    /**
     * 获取分页任务
     * @param page 当前页码
     * @param limit 分页参数
     * @return 分页任务
     */
    Page<Job> findJobByPage(int page, int limit);


    /**
     * 根据任务组，获取任务组所有任务，带分页
     * @param groupId 任务组 id
     * @param page 当前页码
     * @param limit 每页显示多少条信息
     * @return 对应任务组的任务列表
     */
    Page<Job> findJobByGroupId(int groupId, int page, int limit);

    /**
     * 根据运行状态，获取对应运行状态的所有任务，带分页
     * @param runnerType 运行状态：0：已删除，1：已结束，2：已取消，3：已暂停，4：执行中
     * @param page 当前页码
     * @return 对应运行状态的任务列表
     */
    Page<Job> findJobByRunnerType(int runnerType, int page);

    /**
     * 根据任务组、运行状态获取任务信息，带分页
     * @param groupId 任务组
     * @param runnerType 运行状态：0：已删除，1：已结束，2：已取消，3：已暂停，4：执行中
     * @param page 当前页码
     * @return 对应任务列表
     */
    Page<Job> findJobByGroupIdAndRunnerType(int groupId, int runnerType, int page);

    /**
     * 修改任务类型
     * @param runnerType 任务类型
     * @param jobKey 任务 key
     */
    void updateJobRunnerType(int runnerType, String jobKey);

    /**
     * 获对应状态的所有任务
     * @param status 状态
     * @return 所有任务
     */
    List<Job> findAllJobByStatus(int status);

}
