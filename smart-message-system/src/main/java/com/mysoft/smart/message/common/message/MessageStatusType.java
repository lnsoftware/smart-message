package com.mysoft.smart.message.common.message;

/**
 * 消息发送状态类型
 */
public enum MessageStatusType {

    PREPARE_SEND("准备发送"),
    CONFIRM_SEND("确认发送"),
    CONSUME_SUCCESS("成功消费");

    private final String value;

    private MessageStatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
