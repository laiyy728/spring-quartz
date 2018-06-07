package com.laiyy.springquartz.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * @author laiyy
 * @date 2018/6/7 16:57
 * @description
 */
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // JobExecutionContext 类提供了调度的各种信息，为 JobDetail 和 Trigger 提供必要的信息
        // JobKey 是由 name、group 组成，并且 name 必须在 group 内唯一。
        // 如果只指定一组，则将使用默认的组名：DEFAULT

        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("SimpleJob says:" + key + " executing at " + new Date());
    }
}
