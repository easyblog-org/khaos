package top.easyblog.titan.request.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author: frank.huang
 * @date: 2023-03-04 18:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminLogoutRequest {

    @NotBlank(message = "Required param 'token' is not present.")
    private String token;
}
