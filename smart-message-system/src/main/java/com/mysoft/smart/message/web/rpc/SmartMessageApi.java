package com.mysoft.smart.message.web.rpc;

import com.mysoft.smart.message.api.WrapMapper;
import com.mysoft.smart.message.api.Wrapper;
import com.mysoft.smart.message.common.message.MessageStatusType;
import com.mysoft.smart.message.dto.TransactionMessageDto;
import com.mysoft.smart.message.service.TransactionMessageService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 描述: Message RPC调用API
 *
 * @auth lnsof
 * @Date 2018/9/12 20:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/message")
public class SmartMessageApi {

    @Autowired
    private TransactionMessageService transactionMessageService;

    /**
     * 保存预发送消息
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Wrapper<?> save(@Validated(TransactionMessageDto.APIMessageSave.class) @RequestBody TransactionMessageDto bean) {
        int res = transactionMessageService.createTransactionMessage(bean);
        if (res > 0) {
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
        }
        return WrapMapper.error();
    }

    /**
     * 确认状态并发送消息
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public Wrapper<?> confirmAndSend(@Validated(TransactionMessageDto.APIMessageCofirm.class) @RequestBody TransactionMessageDto bean) {
        // 更新消息状态为确认发送
        TransactionMessageDto res = transactionMessageService.confirmMessageStatus(bean.getMessageId());
        if (res != null) {
            // 发送消息
            transactionMessageService.sendMessage(res);
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
        }
        return WrapMapper.error();
    }

    /**
     * 查询状态确认超时的消息
     */
    @RequestMapping(value = "/load-confirm-timeout", method = RequestMethod.POST)
    public Wrapper<?> loadConfirmTimeoutMsg(@Validated(TransactionMessageDto.APIMessageCheckLoad.class) @RequestBody TransactionMessageDto bean) {
        Date dateOffset = null;
        try {
            dateOffset = DateUtils.parseDate(bean.getMessageDate(), "yyyyMMddHHmmss");
        } catch (ParseException e) {
            dateOffset = DateUtils.addMinutes(new Date(), -10);
        }
        List<String> res = transactionMessageService.getMessageByStatusAndDateOffset(MessageStatusType.PREPARE_SEND, dateOffset);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, res);
    }

    /**
     * 查询消费确认超时的消息
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/load-push-timeout", method = RequestMethod.POST)
    public Wrapper<?> loadPushTimeoutMsg(@Validated(TransactionMessageDto.APIMessageCheckLoad.class) @RequestBody TransactionMessageDto bean) {
        Date dateOffset = null;
        try {
            dateOffset = DateUtils.parseDate(bean.getMessageDate(), "yyyyMMddHHmmss");
        } catch (ParseException e) {
            dateOffset = DateUtils.addMinutes(new Date(), -10);
        }
        List<String> res = transactionMessageService.getMessageByStatusAndDateOffset(MessageStatusType.CONFIRM_SEND, dateOffset);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, res);
    }

    /**
     * 确认消息已被成功消费
     *
     * @param bean
     * @return
     */
    public Wrapper<?> completePushMsg(@Validated(TransactionMessageDto.APIMessageCofirm.class) @RequestBody TransactionMessageDto bean) {
        int res = transactionMessageService.consumeMessageSuccess(bean.getMessageId());
        if (res > 0) {
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
        }
        return WrapMapper.error();
    }

}
