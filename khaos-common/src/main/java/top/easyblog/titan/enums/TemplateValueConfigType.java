package top.easyblog.titan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-05 11:51
 */
@Getter
@AllArgsConstructor
public enum TemplateValueConfigType {
    /**
     * 直接值
     */
    DIRECT_VALUE((byte) 1, "直接值"),
    /**
     * JSON 直接值
     */
    DIRECT_JSON_VALUE((byte) 2, "Json直接值"),
    /**
     * 接口直接值
     */
    INTERFACE_DIRECT_VALUE((byte) 3, "接口直接值"),
    /**
     * 接口 JSON 值
     */
    INTERFACE_JSON_VALUE((byte) 4, "接口Json取值"),

    ;

    private final byte code;
    private final String desc;

    public static TemplateValueConfigType codeOf(Byte configType) {
        if (Objects.isNull(configType)) {
            return null;
        }
        return Arrays.stream(TemplateValueConfigType.values()).filter(config -> Objects.equals(config.getCode(), configType))
                .findAny().orElse(null);
    }
}
