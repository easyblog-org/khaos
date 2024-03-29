package top.easyblog.titan.request.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: frank.huang
 * @date: 2023-02-06 20:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryMessageTemplateRequest {
    private String templateCode;
    private String name;
}
