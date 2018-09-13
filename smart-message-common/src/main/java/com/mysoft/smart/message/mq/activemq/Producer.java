package com.mysoft.smart.message.mq.activemq;

import javax.jms.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 描述: ActiveMQ 消息发送者
 *
 * @auth lnsof
 * @Date 2018/9/12 22:25
 * @Version 1.0
 */
@Component
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     *
     * @param destination
     * @param message
     */
    public void sendMessage(String destination, final String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

}
