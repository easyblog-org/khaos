package top.easyblog.titan.bean.message;

import lombok.Data;

import java.util.List;

/**
 * @author: frank.huang
 * @date: 2023-05-05 20:56
 */
@Data
public class MessagePushRuleBean {
    private String code;

    private String businessModule;

    private String businessEvent;

    private String templateCode;

    private String msgGroup;

    private Integer priority;

    private Byte channel;

    private String configIds;

    private List<MessageConfigBean> configs;

    private Boolean deleted;

    private Long createTime;

    private Long updateTime;
}
