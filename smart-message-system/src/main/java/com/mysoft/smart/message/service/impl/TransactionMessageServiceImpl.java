package com.mysoft.smart.message.service.impl;

import com.mysoft.smart.message.dto.TransactionMessageDto;
import com.mysoft.smart.message.entity.TransactionMessage;
import com.mysoft.smart.message.mapper.TransactionMessageMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysoft.smart.message.service.TransactionMessageService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

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

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int saveMessage(TransactionMessageDto messageDto) {
        TransactionMessage entity = new TransactionMessage();
        BeanUtils.copyProperties(messageDto, entity);
        entity.setCreateTime(new Date());
        // 保存数据库
        return transactionMessageMapper.insert(entity);
    }
}
