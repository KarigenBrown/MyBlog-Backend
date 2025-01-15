package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.dto.RoleDto;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.service.RoleService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Response<PageVo> getRoleList(
            // @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("roleName") String roleName,
            @RequestParam("status") String status
    ) {
        List<Role> roles = roleService.getRoleList(pageNum, pageSize, roleName, status);
        PageVo data = new PageVo(roles, Integer.toUnsignedLong(roles.size()));
        return Response.ok(data);
    }

    // @PutMapping("/status")
    @PutMapping("/changeStatus")
    public Response<Object> putRoleStatus(@RequestBody Role role) {
        roleService.putRoleStatus(role);
        return Response.ok();
    }

    @PostMapping
    public Response<Object> postRole(@RequestBody RoleDto RoleDto) {
        Role role = BeanCopyUtils.copyBean(RoleDto, Role.class);
        List<Long> menuIds = RoleDto.getMenuIds().stream()
                .map(Long::parseLong)
                .toList();
        roleService.postRole(role, menuIds);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response<Role> getById(@PathVariable("id") Long id) {
        Role role = roleService.getById(id);
        return Response.ok(role);
    }

    @PutMapping
    public Response<Object> putRole(@RequestBody RoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        List<Long> ids = roleDto.getMenuIds().stream()
                .map(Long::parseLong)
                .toList();
        roleService.putRole(role, ids);
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteById(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return Response.ok();
    }

    // @GetMapping("/list")
    @GetMapping("listAllRole")
    public Response<List<Role>> getRoles() {
        List<Role> roles = roleService.getRoles();
        return Response.ok(roles);
    }
}
