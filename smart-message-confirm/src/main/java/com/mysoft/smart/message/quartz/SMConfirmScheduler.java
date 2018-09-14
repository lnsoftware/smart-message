package com.mysoft.smart.message.quartz;

import com.mysoft.smart.message.quartz.job.SMConfirmJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 描述：定时任务控制中心
 *
 * @Auth yang.m.zhang
 * @Date 9/14/2018 3:53 PM
 * @Version 1.0
 */
@Component
@Configuration
@RefreshScope
public class SMConfirmScheduler {

    @Value("${sm.cofirm.job.corn:0 * * * * ?}")
    private String SMConfirmCorn;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 定时任务入口
     *
     * @throws SchedulerException
     */
    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        confirmMessageJob(scheduler);
    }

    /**
     * 重置定时任务入口
     *
     * @throws SchedulerException
     */
    public void reScheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        resetConfirmMessageJob(scheduler);
    }

    /**
     * Smart Message Confirm Job定时任务
     *
     * @param scheduler
     * @throws SchedulerException
     */
    private void confirmMessageJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(SMConfirmJob.class).withIdentity("SMConfirmJob", Scheduler.DEFAULT_GROUP).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(SMConfirmCorn);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("SMConfirmTrigger", Scheduler.DEFAULT_GROUP).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

//    /**
//     * 通用Job配置
//     *
//     * @param scheduler
//     * @param jobClass
//     * @param jobCorn
//     * @param jobKey
//     * @throws SchedulerException
//     */
//    private void scheduleJob(Scheduler scheduler, Class jobClass, String jobCorn, String jobKey) throws SchedulerException {
//        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey + "Job", Scheduler.DEFAULT_GROUP).build();
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobCorn);
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobKey + "Trigger", Scheduler.DEFAULT_GROUP).withSchedule(scheduleBuilder).build();
//        scheduler.scheduleJob(jobDetail, cronTrigger);
//    }

    /**
     * 重置Smart Message Confirm Job定时任务
     *
     * @throws SchedulerException
     */
    private void resetConfirmMessageJob(Scheduler scheduler) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey("SMConfirmTrigger", Scheduler.DEFAULT_GROUP);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(SMConfirmCorn)).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

//    /**
//     * 通用重置Job配置
//     *
//     * @param scheduler
//     * @param jobCorn
//     * @param jobKey
//     * @throws SchedulerException
//     */
//    private void reScheduleJob(Scheduler scheduler, String jobCorn, String jobKey) throws SchedulerException {
//        TriggerKey triggerKey = TriggerKey.triggerKey(jobKey + "Trigger", Scheduler.DEFAULT_GROUP);
//        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(jobCorn)).build();
//        scheduler.rescheduleJob(triggerKey, trigger);
//    }
}
