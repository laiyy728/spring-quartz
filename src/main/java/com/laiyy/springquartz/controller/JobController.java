package com.laiyy.springquartz.controller;

import com.laiyy.springquartz.base.BaseController;
import com.laiyy.springquartz.dto.AjaxDto;
import com.laiyy.springquartz.enums.JobRunnerType;
import com.laiyy.springquartz.model.Job;
import com.laiyy.springquartz.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author laiyy
 * @date 2018/6/8 14:54
 * @description 任务管理接口
 */
@Controller
@RequestMapping(value = "/api/job")
@Validated
public class JobController extends BaseController<JobService> {

    @GetMapping
    @ResponseBody
    public Page<Job> list(int page, int limit){
        return service.findJobByGroupId(1, page, limit);
    }

    @PutMapping
    @ResponseBody
    public AjaxDto addJob(@Valid Job job) {
        if (!JobRunnerType.hasRunnerType(job.getRunnerType())) {
            logger.debug(">>>>>>>>>>>>>>> 类型不存在 <<<<<<<<<<<<<");
        }
        service.addJob(job);
        return new AjaxDto();
    }


}
