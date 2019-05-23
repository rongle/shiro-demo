package com.shiro.configure;

import com.shiro.entity.*;
import com.shiro.service.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

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

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();

        String username = getUsername(principalCollection);
        User user = userService.queryByName(username);
        List<UserRole> userRoles = userRoleService.queryByUserId(user.getId());

        for (UserRole userRole : userRoles){
            Role role = roleService.queryById(userRole.getRoleId());
            roles.add(role.getName());

//            authorizationInfo.addRole(role.getName());

            List<RolePermission> rolePermissions = rolePermissionService.queryByRoleId(role.getId());

            for (RolePermission rolePermission : rolePermissions){
                Permission permission = permissionService.queryById(rolePermission.getPermissionId());
                permissions.add(permission.getName());

//                authorizationInfo.addStringPermission(permission.getName());
            }

        }
//
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String name = (String)authenticationToken.getPrincipal();
        System.out.println(name);

        User user = userService.queryByName(name);


        if (user == null){
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), "myrealm");
        return simpleAuthenticationInfo;
    }

    protected String getUsername(PrincipalCollection principals) {
        return this.getAvailablePrincipal(principals).toString();
    }
}
