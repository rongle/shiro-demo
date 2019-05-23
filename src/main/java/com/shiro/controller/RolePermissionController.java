package com.shiro.controller;

import com.shiro.entity.RolePermission;
import com.shiro.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RolePermission)表控制层
 *
 * @author makejava
 * @since 2019-05-15 21:27:28
 */
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public List<RolePermission> selectOne(Integer id) {
        return this.rolePermissionService.queryByRoleId(id);
    }

}