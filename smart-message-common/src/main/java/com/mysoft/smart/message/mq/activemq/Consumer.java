package com.mysoft.smart.message.mq.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @auth lnsof
 * @Date 2018/9/12 22:29
 * @Version 1.0
 */
public class Consumer {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }

}
