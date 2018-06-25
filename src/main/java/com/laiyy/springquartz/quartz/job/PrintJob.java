package com.laiyy.springquartz.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author laiyy
 * @date 2018/6/25 16:53
 * @description
 */
public class PrintJob implements Job {

    private static int times = 0;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println(">>>>>>>>>>>>>>> 打印任务： Hello World <<<<<<<<<<<<<<<<<");
        System.err.println(">>>>>>>>>>>>>>> 当前执行次数：" + times++ + "<<<<<<<<<<<<<<<<<");
    }
}
