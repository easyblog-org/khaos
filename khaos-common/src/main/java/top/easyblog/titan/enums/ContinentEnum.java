package top.easyblog.titan.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 洲枚举
 *
 * @author: frank.huang
 * @date: 2023-03-12 15:39
 */
@Getter
@AllArgsConstructor
public enum ContinentEnum {
    GLOBAL(null, "全部"),
    AFRICA("AF", "非洲地区"),
    ANTARCTICA("AN", "南极洲地区"),
    ASIA("AS", "亚洲地区"),
    AUSTRALIA("AU", "澳洲地区"),
    EUROPE("EU", "欧洲地区"),
    NORTH_AMERICA("NA", "北美洲地区"),
    SOUTH_AMERICA("SA", "南美洲地区");

    private final String code;
    private final String desc;

    public static ContinentEnum codeOf(String code) {
        if (StringUtils.isBlank(code)) return GLOBAL;
        return Arrays.stream(ContinentEnum.values()).filter(item -> StringUtils.equalsIgnoreCase(item.getCode(), code)).findAny().orElse(GLOBAL);
    }

}
