package top.easyblog.titan.request.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * @author: frank.huang
 * @date: 2023-05-05 20:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMessagePushRuleRequest {
    private String businessModule;

    private String businessEvent;

    private String templateCode;

    private String msgGroup;

    @Min(value = 0, message = "Param 'priority'  must bigger than 0")
    private Integer priority;

    private Byte channel;

    private String configIds;
}
