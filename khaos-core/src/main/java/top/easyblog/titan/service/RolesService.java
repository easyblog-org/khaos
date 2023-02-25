package top.easyblog.titan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.easyblog.titan.bean.roles.RolesBean;
import top.easyblog.titan.feign.client.RolesClient;
import top.easyblog.titan.request.roles.CreateRolesRequest;
import top.easyblog.titan.request.roles.QueryRolesDetailsRequest;
import top.easyblog.titan.request.roles.QueryRolesListRequest;
import top.easyblog.titan.request.roles.UpdateRolesRequest;
import top.easyblog.titan.response.PageResponse;

/**
 * @author: frank.huang
 * @date: 2023-02-25 16:05
 */
@Slf4j
@Service
public class RolesService {

    @Autowired
    private RolesClient rolesClient;


    public RolesBean details(QueryRolesDetailsRequest request) {
        return rolesClient.request(() -> rolesClient.queryRolesDetails(request));
    }


    public PageResponse<RolesBean> queryRolesList(QueryRolesListRequest request) {
        return rolesClient.request(() -> rolesClient.queryRolesList(request));
    }


    public Object queryAllRoles() {
        return rolesClient.request(() -> rolesClient.queryAllRoles());
    }

    public void createRoles(CreateRolesRequest request) {
        rolesClient.request(() -> rolesClient.createRole(request));
    }

    public void updateRoles(String code, UpdateRolesRequest request) {
        rolesClient.request(() -> rolesClient.updateRole(code, request));
    }

}
