package top.easyblog.titan.request.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: frank.huang
 * @date: 2023-05-05 20:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryMessagePushRuleRequest {
    private String code;
    private String businessModule;
    private String businessEvent;
}
