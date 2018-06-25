package com.laiyy.springquartz.util;

import com.laiyy.springquartz.constants.GlobalConstant;
import com.laiyy.springquartz.enums.JobRunnerType;
import com.laiyy.springquartz.model.Group;
import com.laiyy.springquartz.model.Job;
import com.laiyy.springquartz.quartz.QuartzUtils;
import com.laiyy.springquartz.service.GroupService;
import com.laiyy.springquartz.service.JobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/6/25 11:36
 * @description
 */
@Component
public class InitUtils {

    @Autowired
    private JobService jobService;

    @Autowired
    private GroupService groupService;

    @PostConstruct
    public void init() {
        List<Job> jobList = jobService.findAllJobByStatus(GlobalConstant.NORMAL);
        for (Job job : jobList) {
            Group group = groupService.get(job.getGroupId());
            try {
                QuartzUtils.initJobDetail(job, group);
            } catch (ClassNotFoundException | SchedulerException e) {
                e.printStackTrace();
            }
        }
        try {
            QuartzUtils.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
