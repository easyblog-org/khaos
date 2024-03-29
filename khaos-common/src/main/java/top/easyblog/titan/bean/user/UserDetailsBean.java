package top.easyblog.titan.bean.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.easyblog.titan.bean.account.AccountBean;
import top.easyblog.titan.bean.header.UserHeaderImgBean;
import top.easyblog.titan.bean.login.LoginLogBean;
import top.easyblog.titan.bean.roles.RolesBean;

import java.util.Date;
import java.util.List;

/**
 * Demo Bean
 *
 * @author: frank.huang
 * @date: 2021-11-01 21:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsBean {
    /**
     * 用户Code
     */
    private String code;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 积分
     */
    private Integer integration;
    /**
     * 用户当前有效头像
     */
    private UserHeaderImgBean userCurrentImages;

    /**
     * 用户历史所有头像
     */
    private List<UserHeaderImgBean> userHistoryImages;

    /**
     * 当前账户
     */
    private AccountBean currAccount;

    /**
     * 用户历史所有账号
     */
    private List<AccountBean> accounts;

    /**
     * 当前登录设备列表
     */
    private List<LoginLogBean> signInLogs;
    /**
     * 用户等级
     */
    private Integer level;
    /**
     * 用户（文章）访问量
     */
    private Integer visit;
    /**
     * 用户账号状态，和account状态关联
     */
    private Integer active;
    /**
     * 角色
     */
    private List<RolesBean> roles;
    /**
     * 是否是新注册用户
     */
    private Boolean isNewUser;
    /**
     * 用户注册时间
     */
    private Date createTime;
    /**
     * 最近更新时间
     */
    private Date updateTime;
}
