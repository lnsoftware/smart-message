package com.mysoft.smart.message.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.mysoft.smart.message.common.message.MessageStatusType;
import com.mysoft.smart.message.dto.TransactionMessageDto;
import com.mysoft.smart.message.entity.TransactionMessage;
import com.mysoft.smart.message.mapper.TransactionMessageMapper;
import com.mysoft.smart.message.mq.activemq.Producer;
import com.mysoft.smart.message.service.TransactionMessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * Transaction Message 服务实现类
 * </p>
 *
 * @author yang.m.zhang
 * @since 2018-09-12
 */
@Service
public class TransactionMessageServiceImpl implements TransactionMessageService {

    @Autowired
    private TransactionMessageMapper transactionMessageMapper;

    @Autowired
    private Producer producer;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int createTransactionMessage(TransactionMessageDto messageDto) {
        TransactionMessage entity = new TransactionMessage();
        BeanUtils.copyProperties(messageDto, entity);
        entity.setVersion(1);
        entity.setCreateTime(new Date());
        entity.setStatus(MessageStatusType.PREPARE_SEND.toString());
        // 保存数据库
        return transactionMessageMapper.insert(entity);
    }

    /**
     * 根据消息ID查询transaction message
     *
     * @param messageId
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public TransactionMessageDto getTransactionMessageByMsgId(String messageId) {
        TransactionMessage param = new TransactionMessage();
        param.setMessageId(messageId);
        TransactionMessage transactionMessage = transactionMessageMapper.selectOne(param);
        if (transactionMessage != null) {
            TransactionMessageDto res = new TransactionMessageDto();
            BeanUtils.copyProperties(transactionMessage, res);
            return res;
        }
        return null;
    }

    /**
     * 确认transaction message状态
     *
     * @param messageId
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public TransactionMessageDto confirmMessageStatus(String messageId) {
        TransactionMessage param = new TransactionMessage();
        param.setStatus(MessageStatusType.CONFIRM_SEND.toString());
        int res = transactionMessageMapper.update(param,
                new EntityWrapper<TransactionMessage>().eq("messageId", messageId));
        if (res > 0) {
            return getTransactionMessageByMsgId(messageId);
        }
        return null;
    }

    /**
     * 发送消息
     *
     * @param messageDto
     */
    @Override
    public void sendMessage(TransactionMessageDto messageDto) {
        producer.sendMessage(messageDto.getConsumerQueue(), messageDto.getMessageBody());
    }

    /**
     * 根据消息状态和时间偏移量获取消息
     *
     * @param status
     * @param dateOffset
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public List<String> getMessageByStatusAndDateOffset(MessageStatusType status, Date dateOffset) {
        List<TransactionMessage> transactionMessages = transactionMessageMapper.selectList(new EntityWrapper<TransactionMessage>().
                eq("status", status.toString()).
                le("editTime", dateOffset));
        if (transactionMessages != null && !transactionMessages.isEmpty()) {
            List<String> result = new ArrayList<String>();
            for (TransactionMessage tm : transactionMessages) {
                result.add(tm.getMessageId());
            }
            return result;
        }
        return null;
    }

    /**
     * 确认完成消息的发送
     *
     * @param messageId
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int consumeMessageSuccess(String messageId) {
        TransactionMessage param = new TransactionMessage();
        param.setStatus(MessageStatusType.CONSUME_SUCCESS.toString());
        return transactionMessageMapper.update(param,
                new EntityWrapper<TransactionMessage>().eq("messageId", messageId));
    }
}
