package top.easyblog.titan.enums;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * 用户登录认证类型
 *
 * @author frank.huang
 * @date 2022/01/29 15:09
 */
@Getter
@AllArgsConstructor
public enum IdentifierType {

    UNKNOWN(0, 0, "未知"),

    E_MAIL(1, 10, "Email"),

    PHONE(2, 20, "手机"),

    PHONE_CAPTCHA(2, 21, "手机"),

    QQ(3, 30, "QQ"),

    WECHAT(4, 40, "微信"),

    WEIBO(5, 50, "微博"),

    GITHUB(6, 60, "GitHub"),

    GITEE(7, 70, "Gitee");


    private final Integer code;
    private final Integer subCode;
    private final String desc;


    public static IdentifierType codeOf(Integer code) {
        return Arrays.stream(IdentifierType.values()).filter(type -> Objects.equals(type.code, code)).findAny().orElse(null);
    }

    public static IdentifierType subCodeOf(Integer subCode) {
        return Arrays.stream(IdentifierType.values()).filter(type -> Objects.equals(type.subCode, subCode)).findAny().orElse(null);
    }
}
