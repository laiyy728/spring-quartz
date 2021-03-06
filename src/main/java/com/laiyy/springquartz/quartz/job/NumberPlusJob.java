package com.laiyy.springquartz.quartz.job;

import com.laiyy.springquartz.enums.JobRunnerType;
import com.laiyy.springquartz.service.JobService;
import com.laiyy.springquartz.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author laiyy
 * @date 2018/6/25 10:07
 * @description
 * 注解：
 *  1、@DisallowConcurrentExecution 表示不会并发执行同一个 job 实例。
 *     即：如果有多个 JobDetail 使用了同一个 job 实例，则不会同时执行
 *     该注解应该放在 job 类上
 *  2、@PersistJobDataAfterExecution 执行 job 类的 execute 方法后，且没有发生异常，则
 *      更新 JobDateMap 中的数据，使得该 jobDetail 在下一次执行的时候，jobDateMap 中的数据最新。
 *      该注解放在 job 类上，但是其左右是针对 job 实例的，而不是 job 类。由 jobDetail 来集成该
 *      注解，是因为 jobDetail 的内容经常会影响其行为状态
 *
 *  注意：如果使用了 @PersistJobDataAfterExecution，则建议同时使用 @DisallowConcurrentExecution
 *  因为：当同一个 jobDetail 的两个实例被并发执行，由于竞争关系，jobDataMap 中存储的数据很可能是不确定的
 */
public class NumberPlusJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(NumberPlusJob.class);

    private static JobService jobService = SpringUtil.getBean(JobService.class);

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
