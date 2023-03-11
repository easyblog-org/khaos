package top.easyblog.titan.request.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: frank.huang
 * @date: 2022-02-10 21:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSignInLogRequest {
    /**
     * 登录IP
     */
    private String ip;
    /**
     * 登录设备名称
     */
    private String device;
    /**
     * 登录设备操作系统
     */
    private String operationSystem;
    /**
     * 登录物理定位
     */
    private String location;
}
