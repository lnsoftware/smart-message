package com.mysoft.smart.message.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 描述：
 *
 * @Auth yang.m.zhang
 * @Date 9/14/2018 3:55 PM
 * @Version 1.0
 */
@Slf4j
public class SMConfirmJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello world");
    }
}
