package com.mysoft.smart.message.client.hystrix;

import com.mysoft.smart.message.api.Wrapper;
import com.mysoft.smart.message.bean.TransactionMessageDto;
import com.mysoft.smart.message.client.SmartMessageSystemClient;
import org.springframework.stereotype.Component;

/**
 * 描述：Smart Message System Hystrix
 *
 * @Auth yang.m.zhang
 * @Date 9/14/2018 3:27 PM
 * @Version 1.0
 */
@Component
public class SmartMessageSystemHystrix implements SmartMessageSystemClient {

    /**
     * 查询状态确认超时的消息
     *
     * @param bean
     * @return
     */
    @Override
    public Wrapper<?> loadConfirmTimeoutMsg(TransactionMessageDto bean) {
        return null;
    }
}
