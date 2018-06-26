package com.laiyy.springquartz.repository;

import com.laiyy.springquartz.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/7 20:49
 * @description 任务实体持久化操作
 */
public interface JobRepository extends JpaRepository<Job, Integer> {

    /**
     * 根据 任务 key 获取任务信息
     * @param jobKey 任务 key
     * @return 任务信息
     */
    Job findJobByJobKey(String jobKey);

    /**
     * 根据任务组，获取任务组所有任务，带分页
     * @param groupId 任务组 id
     * @param pageable 分页参数
     * @return 对应任务组的任务列表
     */
    Page<Job> findJobByGroupIdAndStatus(int groupId, int status, Pageable pageable);

    /**
     * 根据运行状态，获取对应运行状态的所有任务，带分页
     * @param runnerType 运行状态：0：已删除，1：已结束，2：已取消，3：已暂停，4：执行中
     * @param pageable 分页信息
     * @return 对应运行状态的任务列表
     */
    Page<Job> findJobByRunnerTypeAndStatus(int runnerType,int status, Pageable pageable);

    /**
     * 根据任务组、运行状态获取任务信息，带分页
     * @param groupId 任务组
     * @param runnerType 运行状态：0：已删除，1：已结束，2：已取消，3：已暂停，4：执行中
     * @param pageable 分页信息
     * @return 对应任务列表
     */
    Page<Job> findJobByGroupIdAndRunnerTypeAndStatus(int groupId, int runnerType,int status, Pageable pageable);

    /**
     * 分页获取任务信息
     * @param pageable 分页参数
     * @return 分页任务信息
     */
    Page<Job> findJobByStatus(int status,Pageable pageable);

    /**
     * 修改任务类型
     * @param runnerType 任务类型
     * @param jobKey 任务 key
     */
    @Modifying
    @Query(value = "update Job set runnerType = :runnerType where jobKey = :jobKey")
    void updateJobRunnerType(@Param("runnerType") int runnerType, @Param("jobKey") String jobKey);

    /**
     * 获取对应运行状态的所有任务
     * @param status 任务状态
     * @return 所有任务
     */
    List<Job> findAllByStatus(int status);

}
