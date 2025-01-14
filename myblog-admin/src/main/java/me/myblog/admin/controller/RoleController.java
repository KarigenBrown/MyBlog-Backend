package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.service.RoleService;
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
    public Response<Object> putRoleStatus(@RequestBody Role role){
        roleService.putRoleStatus(role);
        return Response.ok();
    }
}
