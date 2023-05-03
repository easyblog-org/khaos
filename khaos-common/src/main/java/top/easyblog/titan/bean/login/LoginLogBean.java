package top.easyblog.titan.bean.login;

import lombok.Data;

import java.util.Date;

/**
 * @author frank.huang
 * @date 2022/01/30 10:35
 */
@Data
public class LoginLogBean {

    private String userCode;

    private String token;

    private Integer status;

    private String ipAddress;

    private String device;

    private String operationSystem;

    private String location;

    private Date createTime;

    private Date updateTime;
}
