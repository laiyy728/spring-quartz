package com.laiyy.springquartz.service.impl;

import com.laiyy.springquartz.base.impl.BaseServiceImpl;
import com.laiyy.springquartz.enums.JobRunnerType;
import com.laiyy.springquartz.enums.SortEnum;
import com.laiyy.springquartz.exceptions.ExistsException;
import com.laiyy.springquartz.model.Job;
import com.laiyy.springquartz.repository.JobRepository;
import com.laiyy.springquartz.service.JobService;
import com.laiyy.springquartz.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            logger.debug(">>>>>>>>>>>>>>>>>>>>> 任务已存在，任务 key：{} <<<<<<<<<<<<<<<<<<<<<<<", job.getJobKey());
            throw new ExistsException("该任务已经存在");
        }
        job.setCreateDate(new Date());
        job.setRunnerType(JobRunnerType.PAUSE.type());
        repository.save(job);
    }

    @Override
    public Job findJobByJobKey(String jobKey) {
        logger.debug(">>>>>>>>>>>>>>>>>>>> 正在获取任务，任务 key：{} <<<<<<<<<<<<<<<<<<<<<", jobKey);
        return repository.findJobByJobKey(jobKey);
    }

    @Override
    public Page<Job> findJobByGroupId(int groupId, int page, int limit) {
        logger.debug(">>>>>>>>>>>>>>>正在根据分组 id 获取任务列表，分组 id：{}， 当前页：{} <<<<<<<<<<<<<<<<<", groupId, page);
        return repository.findJobByGroupId(groupId, PageUtil.of(page, limit, SortEnum.DESC, "startDate", "runnerType"));
    }

    @Override
    public Page<Job> findJobByRunnerType(int runnerType, int page) {
        logger.debug(">>>>>>>>>>>>>>>正在根据运行状态获取任务列表，运行状态：{}， 当前页：{} <<<<<<<<<<<<<<<<<", JobRunnerType.runnerName(runnerType), page);
        return repository.findJobByRunnerType(runnerType, PageUtil.of(page, SortEnum.DESC, "startDate, runnerType"));
    }

    @Override
    public Page<Job> findJobByGroupIdAndRunnerType(int groupId, int runnerType, int page) {
        logger.debug(">>>>>>>>>>>>>>>正在根据分组id、运行状态获取任务列表，分组id：{}，运行状态：{}， 当前页：{} <<<<<<<<<<<<<<<<<", groupId, JobRunnerType.runnerName(runnerType), page);
        return repository.findJobByGroupIdAndRunnerType(groupId, runnerType, PageUtil.of(page, SortEnum.DESC, "startDate", "runnerType"));
    }

    @Override
    @Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRED)
    public void updateJobRunnerType(int runnerType, String jobKey) {
        logger.debug(">>>>>>>>>>>>>>>>>>正在修改任务运行状态，任务 key：{}，运行状态：{} <<<<<<<<<<<<<<<<<<<", jobKey, JobRunnerType.runnerName(runnerType));
        repository.updateJobRunnerType(runnerType, jobKey);
    }
}
