package com.laiyy.springquartz.controller;

import com.laiyy.springquartz.dto.AjaxDto;
import com.laiyy.springquartz.enums.JobRunnerType;
import com.laiyy.springquartz.model.Job;
import com.laiyy.springquartz.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyy
 * @date 2018/6/8 14:54
 * @description 任务管理接口
 */
@RestController
@RequestMapping(value = "/pro/job")
public class JobController extends BaseController<JobService>{

    @GetMapping
    public Page<Job> list(int page){
        return service.findJobByGroupId(1, page);
    }

    @PutMapping
    public AjaxDto addJob(Job job) {
        if (!JobRunnerType.hasRunnerType(job.getRunnerType())) {
            logger.debug(">>>>>>>>>>>>>>> 类型不存在 <<<<<<<<<<<<<");
        }
        service.addJob(job);
        return new AjaxDto();
    }


}
