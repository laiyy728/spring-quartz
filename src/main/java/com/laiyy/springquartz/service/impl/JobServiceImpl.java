package com.laiyy.springquartz.service.impl;

import com.laiyy.springquartz.enums.JobRunnerType;
import com.laiyy.springquartz.exceptions.ExistsException;
import com.laiyy.springquartz.model.Job;
import com.laiyy.springquartz.repository.JobRepository;
import com.laiyy.springquartz.service.JobService;
import com.laiyy.springquartz.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author laiyy
 * @date 2018/6/8 11:29
 * @description
 */
@Service
public class JobServiceImpl extends BaseServiceImpl<Job, Integer, JobRepository> implements JobService {

    @Override
    public void addJob(Job job){
        Job oldJob = repository.findJobByJobKey(job.getJobKey());
        if (oldJob != null) {
            throw new ExistsException("该任务已经存在");
        }
        job.setCreateDate(new Date());
        job.setRunnerType(JobRunnerType.PAUSE.type);
        repository.save(job);
    }

    @Override
    public Job findJobByJobKey(String jobKey) {
        return repository.findJobByJobKey(jobKey);
    }

    @Override
    public Page<Job> findJobByGroupId(int groupId, int page) {
        return repository.findJobByGroupId(groupId, PageUtil.of(page));
    }

    @Override
    public Page<Job> findJobByRunnerType(int runnerType, int page) {
        return repository.findJobByRunnerType(runnerType, PageUtil.of(page));
    }

    @Override
    public Page<Job> findJobByGroupIdAndRunnerType(int groupId, int runnerType, int page) {
        return repository.findJobByGroupIdAndRunnerType(groupId, runnerType, PageUtil.of(page));
    }

    @Override
    public void updateJobRunnerType(int runnerType, String jobKey) {
        repository.updateJobRunnerType(runnerType, jobKey);
    }
}
