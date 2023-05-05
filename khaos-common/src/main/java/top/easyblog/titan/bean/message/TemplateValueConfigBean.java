package top.easyblog.titan.bean.message;

import lombok.Data;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:59
 */
@Data
public class TemplateValueConfigBean {
    private Long id;

    private Byte type;

    private String expression;

    private String url;

    private Boolean deleted;

    private Long createTime;

    private Long updateTime;
}
