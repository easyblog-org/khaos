package top.easyblog.titan.request.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author: frank.huang
 * @date: 2023-05-05 20:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryMessagePushRulesRequest {
    private List<Long> ids;

    private Boolean deleted;

    private List<String> businessEvents;

    private List<String> businessModules;

    @Builder.Default
    @Min(value = 0, message = "Param 'offset' can not less than 0")
    private Integer offset = 0;

    @Builder.Default
    @Max(value = 100, message = "Param 'limit' can not bigger than 100")
    private Integer limit = 10;
}
