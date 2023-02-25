package top.easyblog.titan.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;
import top.easyblog.titan.response.KhaosResultCode;

/**
 * @author: frank.huang
 * @date: 2021-11-01 17:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private KhaosResultCode code;

    public BusinessException(@NonNull KhaosResultCode code) {
        this.code = code;
    }

    public BusinessException(@NonNull KhaosResultCode code, String message){
        super(message);
        this.code=code;
    }

    public BusinessException(@NonNull KhaosResultCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }


    public String getCode(){
        return this.code.getCode();
    }

}
