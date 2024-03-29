package top.easyblog.titan.request.message;

import lombok.*;
import top.easyblog.titan.enums.TemplateValueConfigType;
import top.easyblog.titan.request.BaseRequest;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CreateMessageConfigRequest implements BaseRequest {

    @NotBlank(message = "Required param 'type' is not present")
    private String type;

    @NotBlank(message = "Required param 'name' is not present")
    private String name;

    private CreateTemplateValueConfigRequest templateValueConfig;

    @Override
    public boolean validate() {
        assertNotNull(templateValueConfig, "Required param 'templateValueConfig' is not present");
        assertNotNull(templateValueConfig.getType(), "Required param 'templateValueConfig.type' is not present");
        assertNotEmpty(templateValueConfig.getExpression(), "Required param 'templateValueConfig.expression' is not present");
        if (Objects.equals(TemplateValueConfigType.codeOf(templateValueConfig.getType()), TemplateValueConfigType.INTERFACE_DIRECT_VALUE) ||
                Objects.equals(TemplateValueConfigType.codeOf(templateValueConfig.getType()), TemplateValueConfigType.INTERFACE_JSON_VALUE)) {
            assertNotEmpty(templateValueConfig.getUrl(), "Required param 'templateValueConfig.url' is not present");
        }
        return true;
    }
}
