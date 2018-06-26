package com.laiyy.springquartz;

import com.laiyy.springquartz.constants.GlobalConstant;
import com.laiyy.springquartz.model.Group;
import com.laiyy.springquartz.model.Job;
import com.laiyy.springquartz.quartz.QuartzUtils;
import com.laiyy.springquartz.service.GroupService;
import com.laiyy.springquartz.service.JobService;
import com.laiyy.springquartz.util.SpringUtil;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author laiyy
 */
@SpringBootApplication
public class SpringQuartzApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringQuartzApplication.class, args);
        SpringUtil.setApplicationContext(applicationContext);
        // 启动完成后，初始化任务
        initScheduler();
    }

    private static void initScheduler() {
        JobService jobService = SpringUtil.getBean(JobService.class);
        List<Job> jobList = jobService.findAllJobByStatus(GlobalConstant.NORMAL);
        GroupService groupService = SpringUtil.getBean(GroupService.class);
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

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            // WebAppResourceLoader 配置root路径是关键
            WebAppResourceLoader webAppResourceLoader =
                    new WebAppResourceLoader(patternResolver.getResource("classpath:/").getFile().getPath());
            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取配置文件信息
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/templates/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
}
