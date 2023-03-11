package top.easyblog.titan.bean.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.easyblog.titan.bean.user.UserDetailsBean;

/**
 * @author: frank.huang
 * @date: 2022-02-20 22:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDetailsBean {
    protected UserDetailsBean user;
}
