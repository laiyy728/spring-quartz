package com.laiyy.springquartz.test;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author laiyy
 * @date 2018/6/7 16:58
 * @description
 */
public class SimpleTriggerRunner {

    public static void main(String[] args) throws SchedulerException, ClassNotFoundException {
        // 通过 SchedulerFactory 获取一个调度器实例
        StdSchedulerFactory sf = new StdSchedulerFactory();
        // 代表一个 Quartz 的独立运行容器
        Scheduler scheduler = sf.getScheduler();
        // 获取当前时间 15 秒后的时间
        Date startDate = DateBuilder.nextGivenSecondDate(null, 15);
//
//        // job 1：
//        // 创建一个 JobDetail 实例，此版本 JobDetail 已经未做接口存在，通过 JobBuilder 创建，并指定 Job 在 Scheduler 中所属组及名称
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
//        // SimpleTrigger 实现 Trigger 接口的子接口。此处只指定了开始执行定时任务的时间，并使用默认重复次数：0次，重复间隔：0秒（只创建，不执行)
        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startDate).build();
//        // 添加 JobDetail 到 Schedule 容器，并和 Trigger 关联，返回执行时间
        Date date = scheduler.scheduleJob(jobDetail, simpleTrigger);
//        System.out.println(jobDetail.getKey() + " will run at " + date + " and repeat;" + simpleTrigger.getRepeatCount() + "times. every " + simpleTrigger.getRepeatInterval() / 1000L + " seconds");
//
//        // job2：
        jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job2", "group1").build();
        simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startDate).build();
        date = scheduler.scheduleJob(jobDetail, simpleTrigger);
//        System.out.println(jobDetail.getKey() + " will run at " + date + " and repeat;" + simpleTrigger.getRepeatCount() + "times. every " + simpleTrigger.getRepeatInterval() / 1000L + " seconds");
//
//        // job3：
        jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job3", "group1").build();
        // 设置定时任务执行规则：在指定开始时间进行执行，每 10 秒执行一次，总共执行 10 次
        // 当指定为 SimpleScheduleBuilder 时，就不需要强转为 SimpleTrigger 了
        simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").
                startAt(startDate).
                withSchedule(SimpleScheduleBuilder.simpleSchedule().
                        withIntervalInSeconds(10).
                        withRepeatCount(10)).
                build();
        scheduler.scheduleJob(jobDetail,simpleTrigger);
        System.err.println(jobDetail.getKey() + " will run at: " + date + " and repeat: " + simpleTrigger.getRepeatCount()
                + " times, every " + simpleTrigger.getRepeatInterval() / 1000L + " seconds");

        // job3 的实现2：
        // 发出同一个任务，各自执行，上面的执行 10 次，下面的执行 2 次
        simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger3", "group2").startAt(startDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(2).withIntervalInSeconds(10)).forJob(jobDetail).build();
        scheduler.scheduleJob(simpleTrigger);
        System.err.println(jobDetail.getKey() + " will run at: " + date + " and repeat: " + simpleTrigger.getRepeatCount()
                + " times, every " + simpleTrigger.getRepeatInterval() / 1000L + " seconds");

        // job4 ：与 Job 3 一致
        jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job4", "group1").build();
        simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").startAt(startDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(5)).build();
        date = scheduler.scheduleJob(jobDetail, simpleTrigger);
        System.err.println(jobDetail.getKey() + " will run at: " + date + " and repeat: " + simpleTrigger.getRepeatCount()
                + " times, every " + simpleTrigger.getRepeatInterval() / 1000L + " seconds");


        // job 5：
        jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job5", "group1").build();
        simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger5", "group1")
                // 每隔 40 秒执行一次，永远执行下去
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
        date = scheduler.scheduleJob(jobDetail, simpleTrigger);
        System.err.println(jobDetail.getKey() + " will run at: " + date + " and repeat: " + simpleTrigger.getRepeatCount()
                + " times, every " + simpleTrigger.getRepeatInterval() / 1000L + " seconds");

        jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job6", "group1").build();
        // DateBuilder.futureDate(5, DateBuilder.IntervalUnit.MINUTE)  未来5分钟：5分钟后执行一次
        simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger6", "group1").startAt(DateBuilder.futureDate(5, DateBuilder.IntervalUnit.MINUTE)).build();
        date = scheduler.scheduleJob(jobDetail, simpleTrigger);
        System.err.println(jobDetail.getKey() + " will run at: " + date + " and repeat: " + simpleTrigger.getRepeatCount()
                + " times, every " + simpleTrigger.getRepeatInterval() / 1000L + " seconds");

        // 启动定时任务
        scheduler.start();

        // job 7 ：
        jobDetail = JobBuilder.newJob(SimpleJob.class).withIdentity("job7", "group1").build();
        simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").startAt(startDate)
                // 每 5 分钟执行一次，执行 10 次
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).withRepeatCount(10)).build();
        date = scheduler.scheduleJob(jobDetail, simpleTrigger);
        System.err.println(jobDetail.getKey() + " will run at: " + date + " and repeat: " + simpleTrigger.getRepeatCount()
                + " times, every " + simpleTrigger.getRepeatInterval() / 1000L + " seconds");

        // 在 scheduler.start(); 后再次的调用，可以重新注册
        scheduler.start();

    }

}
