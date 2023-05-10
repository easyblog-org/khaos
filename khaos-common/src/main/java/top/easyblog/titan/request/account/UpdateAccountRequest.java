package top.easyblog.titan.request.account;

import lombok.Data;

/**
 * @author frank.huang
 * @date 2022/02/06 09:54
 */
@Data
public class UpdateAccountRequest {
    private String code;

    private String userCode;

    private Integer identityType;

    private String identifier;

    private String credential;

    private Integer status;

    private Integer verified;
}
