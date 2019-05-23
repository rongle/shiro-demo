package com.shiro.controller;

import com.shiro.entity.*;
import com.shiro.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2019-05-14 00:11:03
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private PermissionService permissionService;



    /**
     * 通过主键查询单条数据
     *
     * @param name
     * @return 单条数据
     */

    @GetMapping("selectOne")
    @RequiresPermissions("user:select")
    @ResponseBody
    public Map selectOne(String name) {


        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        User user = userService.queryByName("hdl");
        List<UserRole> userRoles = userRoleService.queryByUserId(user.getId());

        for (UserRole userRole : userRoles){
            Role role = roleService.queryById(userRole.getRoleId());
            roles.add(role.getName());

            List<RolePermission> rolePermissions = rolePermissionService.queryByRoleId(role.getId());

            for (RolePermission rolePermission : rolePermissions){
                Permission permission = permissionService.queryById(rolePermission.getPermissionId());
                permissions.add(permission.getName());
            }

        }


        Map<String, Object> map = new HashMap<>();

        map.put("roles", roles);
        map.put("permissions", permissions);

        return map;
    }

    @GetMapping("deleteuser")
    @RequiresPermissions("user:delete")
    @ResponseBody
    public String deleteUser(int id){
        if (userService.deleteById(id)){
            return "删除成功";
        } else {
            return "删除失败";
        }

    }

    @PostMapping("addUser")
    public Object addUser(HttpServletRequest request){
        User user = new User();
        user.setName("hdl");
        user.setPassword("hdl");
        user.setSalt("eauvau4fuis2");
        user.setEmail("hanrongle@126.com");

        return userService.insert(user);
    }

}