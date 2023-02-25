package top.easyblog.titan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.easyblog.titan.annotation.RequestParamAlias;
import top.easyblog.titan.annotation.ResponseWrapper;
import top.easyblog.titan.request.roles.CreateRolesRequest;
import top.easyblog.titan.request.roles.QueryRolesDetailsRequest;
import top.easyblog.titan.request.roles.QueryRolesListRequest;
import top.easyblog.titan.request.roles.UpdateRolesRequest;
import top.easyblog.titan.service.RolesService;

import javax.validation.Valid;

/**
 * @author: frank.huang
 * @date: 2023-02-25 16:27
 */
@RequestMapping("/v1/roles")
@RestController
public class RolesController {

    @Autowired
    private RolesService userRolesService;

    @ResponseWrapper
    @GetMapping
    public Object query(@Valid @RequestParamAlias QueryRolesDetailsRequest request) {
        return userRolesService.details(request);
    }

    @ResponseWrapper
    @PutMapping("/{code}")
    public void update(@PathVariable("code") String code,
                       @RequestBody @Valid UpdateRolesRequest request) {
        userRolesService.updateRoles(code,request);
    }

    @ResponseWrapper
    @GetMapping("/list")
    public Object queryListPage(@RequestParamAlias QueryRolesListRequest request) {
        return userRolesService.queryRolesList(request);
    }

    @ResponseWrapper
    @PostMapping
    public void create(@RequestBody @Valid CreateRolesRequest request) {
        userRolesService.createRoles(request);
    }

    @ResponseWrapper
    @GetMapping("/all")
    public Object queryAllRole(){
        return userRolesService.queryAllRoles();
    }
}
