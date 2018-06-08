package com.laiyy.springquartz.controller;

import com.laiyy.springquartz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyy
 * @date 2018/6/8 14:54
 * @description 任务管理接口
 */
@RestController
@RequestMapping(value = "/pro/job")
public class JobController {

    @Autowired
    private JobService jobService;




}
