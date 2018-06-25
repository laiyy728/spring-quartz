package com.laiyy.springquartz.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author laiyy
 * @date 2018/6/25 10:07
 * @description
 */
public class NumberPlusJob implements Job {

    private static long number = 1L;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // JobExecutionContext 类提供了调度的各种信息，为 JobDetail 和 Trigger 提供必要的信息
        // JobKey 是由 name、group 组成，并且 name 必须在 group 内唯一。
        // 如果只指定一组，则将使用默认的组名：DEFAULT

        /// JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("数值累加任务，当前数值：" + number++);

    }
}
