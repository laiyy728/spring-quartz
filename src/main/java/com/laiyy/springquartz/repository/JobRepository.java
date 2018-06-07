package com.laiyy.springquartz.repository;

import com.laiyy.springquartz.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author laiyy
 * @date 2018/6/7 20:49
 * @description 任务实体持久化操作
 */
public interface JobRepository extends JpaRepository<Job, Integer> {




}
