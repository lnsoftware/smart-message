package com.mysoft.smart.message.quartz.config;

import com.mysoft.smart.message.quartz.SMConfirmScheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


import lombok.extern.slf4j.Slf4j;

/**
 * 描述： 用于启动Mast Service对Sap的接口的定时任务
 *
 * @Auth yang.m.zhang
 * @Date 9/14/2018 3:55 PM
 * @Version 1.0
 */
@Configuration
@Slf4j
public class SMCSchedulerConfig implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private JobFactory jobFactory;
	
	@Autowired
	private SMConfirmScheduler mstScheduler;
	
	@Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean(); 
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean; 
    }

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			mstScheduler.scheduleJobs();
		} catch (SchedulerException e) {
			log.error("** SMCSchedulerConfig error {} **", e);
		}
	}

}
