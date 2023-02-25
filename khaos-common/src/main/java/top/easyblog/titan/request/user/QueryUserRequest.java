package top.easyblog.titan.request.user;

import lombok.Builder;
import lombok.Data;

/**
 * @author: frank.huang
 * @date: 2022-01-29 21:48
 */
@Data
@Builder
public class QueryUserRequest {
    private Long id;
    private String code;
    private String nickName;
    private String sections;
}
