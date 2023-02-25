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
    AUTH_FAILED,

    SIGN_FAIL,
    SIGN_ERROR,
    SIGN_NOT_FOUND,
    SING_HAS_EXPIRE,
    PARAMETER_VALIDATE_FAILED,

    // 创建user失败
    CREATE_USER_FAILED,
    // user 未找到
    USER_NOT_FOUND,

    REMOTE_INVOKE_FAIL;


    public String getCode() {
        return this.name().toLowerCase();
    }
}
