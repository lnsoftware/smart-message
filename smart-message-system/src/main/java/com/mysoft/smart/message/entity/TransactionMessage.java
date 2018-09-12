package com.mysoft.smart.message.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yang.m.zhang
 * @since 2018-09-12
 */
@Getter
@Setter
@ToString
@TableName("tb_transaction_message")
public class TransactionMessage extends Model<TransactionMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 修改者
     */
    private String editor;
    /**
     * 创建者
     */
    private String creater;
    /**
     * 最后修改时间
     */
    @TableField("edit_time")
    private Date editTime;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 消息ID
     */
    @TableField("message_id")
    private String messageId;
    /**
     * 消息内容
     */
    @TableField("message_body")
    private String messageBody;
    /**
     * 消息数据类型
     */
    @TableField("message_data_type")
    private String messageDataType;
    /**
     * 消费队列
     */
    @TableField("consumer_queue")
    private String consumerQueue;
    /**
     * 消息重发次数
     */
    @TableField("message_send_times")
    private Integer messageSendTimes;
    /**
     * 是否死亡
     */
    @TableField("areadly_dead")
    private String areadlyDead;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扩展字段1
     */
    private String field1;
    /**
     * 扩展字段2
     */
    private String field2;
    /**
     * 扩展字段3
     */
    private String field3;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
