package com.shiro.controller;

import com.shiro.entity.UserRole;
import com.shiro.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserRole)表控制层
 *
 * @author makejava
 * @since 2019-05-15 21:27:28
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public List<UserRole> selectOne(Integer id) {
        return this.userRoleService.queryByUserId(id);
    }

}