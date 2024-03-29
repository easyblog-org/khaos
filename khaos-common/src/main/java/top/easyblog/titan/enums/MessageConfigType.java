package top.easyblog.titan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author: frank.huang
 * @date: 2023-02-05 11:51
 */
@Getter
@AllArgsConstructor
public enum MessageConfigType {
    /**
     * 消息接收人
     */
    RECEIVER("消息接收人"),
    /**
     * 消息正文
     */
    CONTENT("消息正文"),
    /**
     * 消息标题
     */
    SUBJECT("消息标题"),
    ;

    private final String desc;


    public static MessageConfigType of(String configType) {
        if (StringUtils.isEmpty(configType)) {
            return null;
        }
        return Arrays.stream(MessageConfigType.values()).filter(config -> StringUtils.equalsIgnoreCase(config.name(), configType))
                .findAny().orElse(null);
    }
}
