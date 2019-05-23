package com.shiro.service.impl;

import com.shiro.entity.RolePermission;
import com.shiro.dao.RolePermissionDao;
import com.shiro.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RolePermission)表服务实现类
 *
 * @author makejava
 * @since 2019-05-15 21:27:28
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionDao rolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public List<RolePermission> queryByRoleId(Integer roleId) {
        return this.rolePermissionDao.queryByRoleId(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RolePermission> queryAllByLimit(int offset, int limit) {
        return this.rolePermissionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param rolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public RolePermission insert(RolePermission rolePermission) {
        this.rolePermissionDao.insert(rolePermission);
        return rolePermission;
    }

//    /**
//     * 修改数据
//     *
//     * @param rolePermission 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public RolePermission update(RolePermission rolePermission) {
//        this.rolePermissionDao.update(rolePermission);
//        return this.queryById(rolePermission.getRoleId());
//    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.rolePermissionDao.deleteById(roleId) > 0;
    }
}