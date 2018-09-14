package com.mysoft.smart.message.client;

import com.mysoft.smart.message.api.Wrapper;
import com.mysoft.smart.message.bean.TransactionMessageDto;
import com.mysoft.smart.message.client.hystrix.SmartMessageSystemHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述：Smart Message System Feight Client
 *
 * @Auth yang.m.zhang
 * @Date 9/14/2018 3:19 PM
 * @Version 1.0
 */
@FeignClient(serviceId = "smart-message-system", fallback = SmartMessageSystemHystrix.class)
public interface SmartMessageSystemClient {

    /**
     * 查询状态确认超时的消息
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/api/message/load-confirm-timeout", method = RequestMethod.POST)
    public Wrapper<?> loadConfirmTimeoutMsg(TransactionMessageDto bean);
}
