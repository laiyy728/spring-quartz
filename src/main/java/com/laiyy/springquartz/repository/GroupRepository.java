package com.laiyy.springquartz.repository;

import com.laiyy.springquartz.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/21 16:12
 * @description 任务组
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {

    /**
     * 修改任务状态
     * @param status 状态，0：删除，1：禁用，2：启用
     * @param id 任务组id
     */
    @Modifying
    @Query(value = "update Group set status = :status where id = :id")
    void updateGroupStatus(@Param("status") int status,
                           @Param("id") int id);

    /**
     * 根据任务组状态获取任务所有任务组
     * @param status 任务组状态
     * @return 对应状态的所有任务组
     */
    List<Group> findAllByStatusOrderByCreateDateDesc(int status);

    /**
     * 根据状态获取分页数据
     * @param status 状态
     * @param pageable 分页参数
     * @return 分页数据
     */
    Page<Group> findGroupByStatus(int status, Pageable pageable);

    /**
     * 根据状态获取分页数据
     * @param status 状态
     * @param pageable 分页参数
     * @param name 组名
     * @return 分页数据
     */
    Page<Group> findGroupByStatusAndNameLike(int status, String name, Pageable pageable);

}
