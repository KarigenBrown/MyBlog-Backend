package me.myblog.admin.controller;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.dto.UserDetailVo;
import me.myblog.framework.domain.dto.UserDto;
import me.myblog.framework.domain.entity.Menu;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.domain.vo.*;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.service.MenuService;
import me.myblog.framework.service.RoleService;
import me.myblog.framework.service.UserService;
import me.myblog.framework.utils.BeanCopyUtils;
import me.myblog.framework.utils.RedisCacheUtils;
import me.myblog.framework.utils.SecurityUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    // @GetMapping("/list")
    @GetMapping("/system/user/list")
    public Response<PageVo> getUserList(
            // @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("userName") String userName,
            @RequestParam("phonenumber") String phonenumber,
            @RequestParam("status") String status
    ) {
        List<User> users = userService.getUserList(pageNum, pageSize, userName, phonenumber, status);
        PageVo pageVo = new PageVo(users, Integer.toUnsignedLong(users.size()));
        return Response.ok(pageVo);
    }

    // @PostMapping
    @PostMapping("/system/user")
    public Response<Object> postUser(@RequestBody UserDto userDto) {
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        List<Long> ids = userDto.getRoleIds().stream()
                .map(Long::parseLong)
                .toList();
        userService.postUser(user, ids);
        return Response.ok();
    }

    // @DeleteMapping
    @DeleteMapping("/system/user/{id}")
    public Response<Object> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return Response.ok();
    }

    // @GetMapping("/{id}")
    @GetMapping("/system/user/{id}")
    public Response<UserVo> getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        UserDetailVo userDetailVo = BeanCopyUtils.copyBean(user, UserDetailVo.class);
        List<Role> roles = user.getRoles();
        List<String> ids = roles.stream()
                .map(Role::getId)
                .map(Objects::toString)
                .toList();
        UserVo userVo = new UserVo();
        userVo.setUserDetail(userDetailVo);
        userVo.setRoles(roles);
        userVo.setRoleIds(ids);
        return Response.ok(userVo);
    }
}
