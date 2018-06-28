package com.laiyy.springquartz.controller;

import com.laiyy.springquartz.base.BaseController;
import com.laiyy.springquartz.enums.JobRunnerType;
import com.laiyy.springquartz.exceptions.GlobalException;
import com.laiyy.springquartz.model.Group;
import com.laiyy.springquartz.model.Job;
import com.laiyy.springquartz.quartz.QuartzUtils;
import com.laiyy.springquartz.service.GroupService;
import com.laiyy.springquartz.service.JobService;
import com.laiyy.springquartz.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;

/**
 * @author laiyy
 * @date 2018/6/8 14:54
 * @description 任务管理接口
 */
@Controller
@RequestMapping(value = "/api/job")
@Validated
public class JobController extends BaseController<JobService> {

    @Autowired
    private GroupService groupService;

    /**
     * 任务列表
     *
     * @param page  当前页
     * @param limit 每页显示条数
     * @return 任务列表
     */
    @GetMapping
    @ResponseBody
    public Page<Job> list(int page, int limit) {
        return service.findJobByPage(page, limit);
    }

    /**
     * 任务添加
     *
     * @param job 添加的任务
     * @return 添加后的任务
     * @throws SchedulerException     可能出现的异常
     * @throws ClassNotFoundException 可能出现的异常
     */
    @PostMapping
    @ResponseBody
    public Job addJob(@Valid Job job) throws SchedulerException, ClassNotFoundException {
        String time = job.getTime();
        if (StringUtils.isBlank(time)) {
            throw new GlobalException("请选择开始时间");
        }
        try {
            Date startDate = DateUtils.parseDate(job.getTime(), "yyyy-MM-dd HH:mm:ss");
            if (startDate.getTime() < System.currentTimeMillis()) {
                throw new GlobalException("开始时间不能小于当前时间");
            }
            job.setStartDate(startDate);
        } catch (ParseException e) {
            throw new GlobalException("时间格式为：yyyy-MM-dd HH:mm:ss");
        }
        job = service.addJob(job);
        Group group = groupService.get(job.getGroupId());
        QuartzUtils.addJob(job, group);
        // TODO: 2018/6/25 添加到定时任务重
        return job;
    }

    @GetMapping(value = "ttl/{id}")
    @ResponseBody
    public String ttl(@PathVariable int id) {
        Job job = service.get(id);
        Group group = groupService.get(job.getGroupId());
        long ttl;
        try {
            ttl = QuartzUtils.ttl(id, group.getName());
        } catch (SchedulerException e) {
            return "查询出错";
        }
        if (ttl == -1) {
            // 任务已经结束
            return "任务已结束";
        }
        return "距下次执行还有：" + TimeUtils.ttl(ttl);
    }

    @PutMapping(value = "pause/{id}")
    @ResponseBody
    public void pause(@PathVariable int id){
        Job job = service.get(id);
        service.updateJobRunnerType(JobRunnerType.PAUSE.type(),job.getJobKey());
        try {
            QuartzUtils.pause(job.getJobKey());
        } catch (SchedulerException e) {
            throw new GlobalException(e.getLocalizedMessage());

        }
    }

    @PutMapping(value = "/resume/{id}")
    @ResponseBody
    public void resume(@PathVariable int id){
        Job job = service.get(id);
        service.updateJobRunnerType(JobRunnerType.RUNNING.type(), job.getJobKey());
        try {
            QuartzUtils.resume(job.getJobKey());
        } catch (SchedulerException e) {
            throw new GlobalException(e.getLocalizedMessage());

        }
    }

    @PutMapping(value = "cancel/{id}")
    @ResponseBody
    public void delete(@PathVariable int id){
        Job job = service.get(id);
        service.updateJobRunnerType(JobRunnerType.CANCEL.type(), job.getJobKey());
        try {
            QuartzUtils.cancel(job.getJobKey());
        } catch (SchedulerException e){
            throw new GlobalException(e.getLocalizedMessage());
        }
    }

}
