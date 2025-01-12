package me.myblog.admin.controller;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Menu;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.domain.vo.AdminInfoVo;
import me.myblog.framework.domain.vo.RoutesVo;
import me.myblog.framework.domain.vo.UserInfoVo;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.service.MenuService;
import me.myblog.framework.service.RoleService;
import me.myblog.framework.service.UserService;
import me.myblog.framework.utils.BeanCopyUtils;
import me.myblog.framework.utils.RedisCacheUtils;
import me.myblog.framework.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
// @RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public Response<Map<String, String>> loginAdmin(@RequestBody User admin) {
        if (!StringUtils.hasText(admin.getUsername())) {
            // 提示，必须要传用户名
            throw new SystemException(ResponseStatusEnum.REQUIRE_USERNAME);
        }
        Map<String, String> result = userService.loginAdmin(admin);
        return Response.ok(result);
    }

    // @GetMapping("/info")
    @GetMapping("/getInfo")
    public Response<AdminInfoVo> getAdminInfo() {
        // 获取当前登录的用户
        User admin = SecurityUtils.getLoginUser();
        // 根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(admin.getId());
        // 根据用户id查询角色信息
        List<String> roleKeys = roleService.selectRoleKeyByUserId(admin.getId());
        // 获取用户信息
        UserInfoVo userinfo = BeanCopyUtils.copyBean(admin, UserInfoVo.class);
        // 封装数据返回
        AdminInfoVo data = new AdminInfoVo();
        data.setPermissions(perms);
        data.setRoles(roleKeys);
        data.setUser(userinfo);
        return Response.ok(data);
    }

    // @GetMapping("/routes")
    @GetMapping("/getRouters")
    public Response<RoutesVo> getAdminRoutes() {
        Long adminId = SecurityUtils.getUserId();
        // 查询menu，结果是tree的形式
        List<Menu> menus = menuService.selectRoutesMenuTreeByUserId(adminId);
        // 封装数据返回
        return Response.ok(new RoutesVo(menus));
    }

    // @DeleteMapping("/logout")
    @PostMapping("/user/logout")
    public Response<Object> logout() {
        userService.logoutAdmin();
        return Response.ok();
    }
}
