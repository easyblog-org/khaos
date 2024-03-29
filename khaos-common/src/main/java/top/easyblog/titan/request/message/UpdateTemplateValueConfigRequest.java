package top.easyblog.titan.request.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTemplateValueConfigRequest {
    private Byte type;

    private String expression;

    private String url;

    private Boolean deleted;
}
