package com.mysoft.smart.message.web.rpc;

import com.mysoft.smart.message.api.WrapMapper;
import com.mysoft.smart.message.api.Wrapper;
import com.mysoft.smart.message.dto.TransactionMessageDto;
import com.mysoft.smart.message.service.TransactionMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Wrapper<?> save(@RequestBody TransactionMessageDto bean) {
        int res = transactionMessageService.saveMessage(bean);
        if (res > 0) {
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE);
        }
        return WrapMapper.error();
    }

}
