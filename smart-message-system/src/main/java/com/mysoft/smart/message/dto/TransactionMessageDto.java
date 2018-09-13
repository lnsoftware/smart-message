package com.mysoft.smart.message.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

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

    @NotBlank(message = "{message.messageId.empty.msg}", groups = {APIMessageSave.class, APIMessageCofirm.class})
    private String messageId;

    @NotBlank(message = "{message.messageBody.empty.msg}", groups = {APIMessageSave.class})
    private String messageBody;

    @NotBlank(message = "{message.messageBody.empty.msg}", groups = {APIMessageSave.class})
    private String messageDataType;

    @NotBlank(message = "{message.consumerQueue.empty.msg}", groups = {APIMessageSave.class})
    private String consumerQueue;

    private Integer messageSendTimes;

    private String areadlyDead;

    //@NotBlank(message = "{message.messageBody.empty.msg}", groups = {APIMessageSave.class})
    private String status;

    private String remark;

    public static interface APIMessageSave{}

    public static interface APIMessageCofirm{}

}
