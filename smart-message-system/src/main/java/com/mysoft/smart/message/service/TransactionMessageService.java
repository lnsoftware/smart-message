package com.mysoft.smart.message.service;

import com.mysoft.smart.message.dto.TransactionMessageDto;
import com.mysoft.smart.message.entity.TransactionMessage;
import com.baomidou.mybatisplus.service.IService;

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
    public int saveMessage(TransactionMessageDto messageDto);

}
