package com.laiyy.springquartz.quartz;

import com.google.common.collect.Maps;
import com.laiyy.springquartz.model.Group;
import com.laiyy.springquartz.model.Job;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimeZone;

/**
 * @author laiyy
 * @date 2018/6/25 10:12
 * @description
 */
@SuppressWarnings("unchecked")
public class QuartzUtils {

    private static final Logger logger = LoggerFactory.getLogger(QuartzUtils.class);

    private static JobDetail jobDetail;

    private static Trigger trigger;

    private static Scheduler scheduler;

    private static Map<String, JobDetail> jobMaps = Maps.newConcurrentMap();
    private static Map<String, Trigger> triggerMap = Maps.newConcurrentMap();


    static {
        // 初始化任务工厂
        StdSchedulerFactory sf = new StdSchedulerFactory();
        try {
            scheduler = sf.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 任务初始化
     *
     * @param job   任务属性
     * @param group 组属性
     * @throws ClassNotFoundException 可能出现的异常
     * @throws SchedulerException     可能出现的异常
     */
    public static void initJobDetail(Job job, Group group) throws ClassNotFoundException, SchedulerException {
        jobDetail(job, group);
        initTrigger(job, group);
        scheduler.scheduleJob(jobDetail, trigger);

        jobMaps.put(job.getJobKey(), jobDetail);
        triggerMap.put(job.getJobKey(), trigger);
        logger.debug(">>>>>>>>>>>>>>> 初始化定时任，任务key：{}，任务组：{} <<<<<<<<<<<<<<<<<<", job.getJobKey(), group.getName());
    }

    private static void initTrigger(Job job, Group group) {
        // 先判断运行几次
        int times = job.getTimes();
        switch (times) {
            case -1:
                // 永远运行
                triggerForever(job, group.getName());
                break;
            case 0:
                // 运行一次
                triggerOnceTime(job.getId(), group.getName());
                break;
            default:
                // 运行多次，固定次数
                triggerTimes(job, group.getName());
                break;
        }
    }

    /**
     * 添加任务
     *
     * @param job   任务属性
     * @param group 组属性
     * @throws ClassNotFoundException 可能出现的异常
     * @throws SchedulerException     可能出现的异常
     */
    public static void addJob(Job job, Group group) throws ClassNotFoundException, SchedulerException {
        jobDetail(job, group);
        initTrigger(job, group);
        scheduler.scheduleJob(jobDetail, trigger);
        start();
        logger.debug(">>>>>>>>>>>>>>>>>>> 添加一条定时任务信息成功，任务key：{} <<<<<<<<<<<<<<<<<<<<<", job.getJobKey());
    }

    /**
     * 下一次运行时间
     * @param jobKey 任务key
     * @return 下一次运行时间
     */
    public static long ttl(String jobKey) {
        trigger = triggerMap.get(jobKey);
        return trigger.getNextFireTime().getTime();
    }

    /**
     * 恢复任务
     *
     * @param key 任务key
     * @throws UnableToInterruptJobException 可能出现异常
     */
    public static void interrupt(String key) throws UnableToInterruptJobException {
        jobDetail = jobMaps.get(key);
        JobKey jobKey = jobDetail.getKey();
        scheduler.interrupt(jobKey);
        logger.debug(">>>>>>>>>>>>>>>>>> 恢复定时任务成功，任务key：{} <<<<<<<<<<<<<<<<", key);
    }

    /**
     * 取消、删除任务
     *
     * @param key 任务key
     * @throws SchedulerException 可能出现异常
     */
    public static void cancel(String key) throws SchedulerException {
        jobDetail = jobMaps.get(key);
        JobKey jobKey = jobDetail.getKey();
        scheduler.deleteJob(jobKey);
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>> 删除、取消定时任务成功，任务key：{} <<<<<<<<<<<<<<<<<<<", key);
        jobMaps.remove(key);
        triggerMap.remove(key);
    }


    /**
     * 暂停任务
     *
     * @param key 任务key
     * @throws SchedulerException 可能出现异常
     */
    public static void pause(String key) throws SchedulerException {
        jobDetail = jobMaps.get(key);
        JobKey jobKey = jobDetail.getKey();
        scheduler.pauseJob(jobKey);
        logger.debug(">>>>>>>>>>>>>>>>>>> 暂停定时任务成功，任务key：{}<<<<<<<<<<<<<<<<<<<", key);
    }

    /**
     * 开始任务
     *
     * @throws SchedulerException 可能会出现异常
     */
    public static void start() throws SchedulerException {
        scheduler.start();
        logger.debug(">>>>>>>>>>>>>> 定时任务启动成功 <<<<<<<<<<<<<<<<");
    }

    private static void jobDetail(Job job, Group group) throws ClassNotFoundException {
        jobDetail = JobBuilder.newJob((Class<? extends org.quartz.Job>) ClassUtils.getClass(job.getRunnerClassName()))
                .withIdentity(job.getJobKey(), group.getName()).build();
    }

    /**
     * 没有指定 cron，运行一次
     *
     * @param groupName 组名称
     * @param jobId 任务id
     */
    private static void triggerOnceTime(int jobId, String groupName) {
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + jobId, groupName).startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND)).build();
        logger.debug(">>>>>>>>>>>>>>>>>>>> 单次执行任务，执行时间：{} <<<<<<<<<<<<<<<", DateFormatUtils.format(System.currentTimeMillis() + 5, "yyyy年MM月ddd日 HH时mm分ss秒"));
    }

    /**
     * 永远运行
     *
     * @param job       任务属性，获取运行周期
     * @param groupName 组名称
     */
    private static void triggerForever(Job job, String groupName) {

        String cron = job.getCron();
        if (StringUtils.isNotBlank(cron)) {
            // cron
            trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + job.getId(), groupName).withSchedule(
                    CronScheduleBuilder.cronSchedule(cron).inTimeZone(TimeZone.getDefault())
            ).startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND)).build();
            // 延迟 5 秒执行
            logger.debug(">>>>>>>>>>>>>>>>>>>> 永久执行任务，执行时间：{} <<<<<<<<<<<<<<<", DateFormatUtils.format(System.currentTimeMillis() + 5, "yyyy年MM月ddd日 HH时mm分ss秒"));
            logger.debug(">>>>>>>>>>>>>>>>>>>> 执行 cron：{} <<<<<<<<<<<<<<<<<", cron);
        } else {
            // simple
            // 计算运行周期
            trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + job.getId(), groupName)
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(second(job)).repeatForever()
                    ).startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND)).build();
            // 延迟 5 秒执行
            logger.debug(">>>>>>>>>>>>>>>>>>>> 永久执行任务，执行时间：{} <<<<<<<<<<<<<<<", DateFormatUtils.format(System.currentTimeMillis() + 5, "yyyy年MM月ddd日 HH时mm分ss秒"));
            logger.debug(">>>>>>>>>>>>>>>>>>>> 执行间隔：{} 秒 <<<<<<<<<<<<<<<<<", second(job));
        }

    }

    /**
     * 运行固定次数
     *
     * @param job       获取运行次数
     * @param groupName 组名称
     */
    private static void triggerTimes(Job job, String groupName) {
        trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + job.getId(), groupName)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(second(job))
                                .withRepeatCount(job.getTimes())
                ).startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND)).build();
        // 延迟 5 秒执行
        logger.debug(">>>>>>>>>>>>>>>>>>>> 多次执行任务，执行时间：{} <<<<<<<<<<<<<<<", DateFormatUtils.format(System.currentTimeMillis() + 5, "yyyy年MM月ddd日 HH时mm分ss秒"));
        logger.debug(">>>>>>>>>>>>>>>>>>>> 执行间隔：{} 秒，执行总次数：{} <<<<<<<<<<<<<<<<<", second(job), job.getTimes());
    }


    /**
     * 计算运行周期：秒
     *
     * @param job 任务属性，获取运行周期
     * @return 计算后的运行周期
     */
    private static int second(Job job) {
        return job.getSecond() + job.getMinute() * 60 + job.getHour() * 60 * 60
                + job.getDay() * 60 * 60 * 24
                + job.getMonth() * 60 * 60 * 24 * 30
                + job.getYear() * 60 * 60 * 24 * 365;
    }

}
