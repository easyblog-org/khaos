package top.easyblog.titan.bean.message;

import lombok.Data;

/**
 * @author: frank.huang
 * @date: 2023-02-06 19:29
 */
@Data
public class MessageConfigRuleBean {
    private Long id;

    private String code;

    private String businessModule;

    private String businessEvent;

    private String templateCode;

    private String msgGroup;

    private Integer priority;

    private Byte channel;

    private String configIds;

    private Boolean deleted;

    private Long createTime;

    private Long updateTime;
}
