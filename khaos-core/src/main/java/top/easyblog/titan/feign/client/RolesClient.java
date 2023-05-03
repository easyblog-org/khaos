package top.easyblog.titan.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.bean.roles.RolesBean;
import top.easyblog.titan.feign.config.CommonFeignConfig;
import top.easyblog.titan.feign.internal.BaseClientResponse;
import top.easyblog.titan.feign.internal.Verify;
import top.easyblog.titan.request.roles.CreateRolesRequest;
import top.easyblog.titan.request.roles.QueryRolesDetailsRequest;
import top.easyblog.titan.request.roles.QueryRolesListRequest;
import top.easyblog.titan.request.roles.UpdateRolesRequest;
import top.easyblog.titan.response.PageResponse;

import java.util.List;

/**
 * @author: frank.huang
 * @date: 2023-02-25 14:27
 */
@FeignClient(name = "roles", url = "${urls.easyblog}", configuration = CommonFeignConfig.class)
public interface RolesClient extends Verify {

    /**
     * 创建角色
     *
     * @param request
     * @return
     */
    @PostMapping("/v1/in/roles")
    BaseClientResponse<Void> createRole(@RequestBody CreateRolesRequest request);

    /**
     * 更新角色
     *
     * @param code
     * @param request
     * @return
     */
    @PutMapping("/v1/in/roles/{code}")
    BaseClientResponse<Void> updateRole(@PathVariable("code") String code,
                                        @RequestBody UpdateRolesRequest request);

    /**
     * 查询角色详情
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/roles")
    BaseClientResponse<RolesBean> queryRolesDetails(@SpringQueryMap QueryRolesDetailsRequest request);

    /**
     * 查询角色详情
     *
     * @param request
     * @return
     */
    @GetMapping("/v1/in/roles/list")
    BaseClientResponse<PageResponse<RolesBean>> queryRolesList(@SpringQueryMap QueryRolesListRequest request);


    /**
     * 查询角色全量数据
     *
     * @return
     */
    @GetMapping("/v1/in/roles/all")
    BaseClientResponse<List<RolesBean>> queryAllRoles();
}
