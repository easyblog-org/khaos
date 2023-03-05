package top.easyblog.titan.feign.internal;

import org.apache.commons.lang3.StringUtils;
import top.easyblog.titan.exception.BusinessException;
import top.easyblog.titan.response.KhaosResultCode;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author: frank.huang
 * @date: 2021-11-14 21:29
 */
public interface Verify {

    /**
     * 使用request方法进行feign调用
     *
     * @param request
     * @param <T>
     * @return
     */
    default <T> T request(Supplier<Response<T>> request) {
        Response<T> response = request.get();
        this.throwIfFail(response, determineResultCode(response));
        return response.data();
    }


    default <T> KhaosResultCode determineResultCode(Response<T> response) {
        return Arrays.stream(KhaosResultCode.values())
                .filter(code -> StringUtils.equalsIgnoreCase(code.name(), response.resultCode())).findAny().orElse(KhaosResultCode.REMOTE_INVOKE_FAIL);
    }

    default <T> void throwIfFail(Response<T> response, KhaosResultCode resultCode) {
        if (Objects.isNull(response) || !response.isSuccess()) {
            throw new BusinessException(resultCode, response.message());
        }
    }

}
