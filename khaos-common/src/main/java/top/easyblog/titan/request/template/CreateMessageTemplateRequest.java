package top.easyblog.titan.request.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: frank.huang
 * @date: 2023-02-11 14:52
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageTemplateRequest {

    @NotBlank(message = "Required param 'name' is not present.")
    private String name;

    private String expectPushTime;

    @NotNull(message = "Required param 'msg_type' is not present.")
    private Byte msgType;

    @NotNull(message = "Required param 'shield_type' is not present.")
    private Byte shieldType;

    @NotBlank(message = "Required param 'msg_content' is not present.")
    private String msgContent;

}
