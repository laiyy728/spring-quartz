package com.laiyy.springquartz.quartz.job;

import com.laiyy.springquartz.enums.JobRunnerType;
import com.laiyy.springquartz.service.JobService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author laiyy
 * @date 2018/6/25 10:07
 * @description
 */
@Component
public class NumberPlusJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(NumberPlusJob.class);

    @Autowired
    private JobService jobService;

    private static long number = 1L;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // JobExecutionContext 类提供了调度的各种信息，为 JobDetail 和 Trigger 提供必要的信息
        // JobKey 是由 name、group 组成，并且 name 必须在 group 内唯一。
        // 如果只指定一组，则将使用默认的组名：DEFAULT

        Trigger trigger = jobExecutionContext.getTrigger();
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobKey key = jobDetail.getKey();
        String jobKey = key.toString();
        if (!trigger.mayFireAgain()) {
            logger.debug(">>>>>>>>>>>>>>{} 任务结束，修改状态为已结束 <<<<<<<<<<<<<<<", jobKey);
            // 如果不能进行下一次执行，则修改 job 的状态
            jobService.updateJobRunnerType(JobRunnerType.OVER.type(), StringUtils.substringAfterLast(jobKey, "."));
            // 打印日志

        }

        logger.debug("数值累加任务，当前数值：{}", number++);

    }
}
