package top.easyblog.titan.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * System unified response code
 *
 * @author frank.huang
 * @since 2021/8/21 22:13
 */
@Getter
@AllArgsConstructor
public enum KhaosResultCode {
    //sever internal
    SUCCESS,
    FAIL,
    INVALID_PARAMS,
    NOT_FOUND,
    INTERNAL_ERROR,
    DATA_ACCESS_FAIL,
    AUTH_EXPIRED,

    SIGN_FAIL,
    SIGN_ERROR,
    SIGN_NOT_FOUND,
    SING_HAS_EXPIRE,
    PARAMETER_VALIDATE_FAILED,

    // 非法洲Code
    INVALID_CONTINENT_TYPE,
    // phone area code已经存在
    PHONE_AREA_CODE_ALREADY_EXISTS,
    // 删除操作权限失败
    DELETE_OPERATION_NOT_PERMISSION,

    // 创建user失败
    CREATE_USER_FAILED,
    // user 未找到
    USER_NOT_FOUND,
    // 请求未携带Token
    AUTH_TOKEN_NOT_FOUND,
    // 账户未找到
    ACCOUNT_NOT_FOUND,
    //账户未激活
    ACCOUNT_IS_PRE_ACTIVE,
    //账户被删除
    ACCOUNT_IS_DELETE,
    //账户被冻结
    ACCOUNT_IS_FREEZE,
    // 密码错误
    PASSWORD_VALID_FAILED,
    // 新老密码一致
    PASSWORD_NOT_CHANGE,
    // 没有访问权限
    NO_ACCESS_PERMISSION,

    REMOTE_INVOKE_FAIL;


    public String getCode() {
        return this.name().toLowerCase();
    }
}
