package com.mysoft.smart.message.service;

import com.mysoft.smart.message.common.message.MessageStatusType;
import com.mysoft.smart.message.dto.TransactionMessageDto;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Transaction Message 服务类
 * </p>
 *
 * @author yang.m.zhang
 * @since 2018-09-12
 */
public interface TransactionMessageService {

    /**
     * 保存消息到数据库
     *
     * @param messageDto
     * @return
     */
    public int createTransactionMessage(TransactionMessageDto messageDto);

    /**
     * 根据消息ID查询transaction message
     *
     * @param messageId
     * @return
     */
    public TransactionMessageDto getTransactionMessageByMsgId(String messageId);

    /**
     * 确认transaction message状态
     *
     * @param messageId
     * @return
     */
    public TransactionMessageDto confirmMessageStatus(String messageId);

    /**
     * 发送消息
     *
     * @param messageDto
     */
    public void sendMessage(TransactionMessageDto messageDto);

    /**
     * 根据消息状态和时间偏移量获取消息Id
     *
     * @param status
     * @param offset
     * @return
     */
    public List<String> getMessageByStatusAndDateOffset(MessageStatusType status, Date offset);

    /**
     * 确认完成消息的发送
     *
     * @param messageId
     * @return
     */
    public int consumeMessageSuccess(String messageId);
}
