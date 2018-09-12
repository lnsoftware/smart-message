package com.mysoft.smart.message.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 描述:
 *
 * @auth lnsof
 * @Date 2018/9/12 21:08
 * @Version 1.0
 */
@Getter
@Setter
@ToString
public class TransactionMessageDto {

    private Long id;

    private Integer version;

    private String editor;

    private String creater;

    private String editTime;

    private String createTime;

    private String messageId;

    private String messageBody;

    private String messageDataType;

    private String consumerQueue;

    private Integer messageSendTimes;

    private String areadlyDead;

    private String status;

    private String remark;

}
