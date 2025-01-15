package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Menu;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
// @RequestMapping("/menu")
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Response<List<Menu>> getMenuList(
            @RequestParam("status") String status,
            @RequestParam("menuName") String menuName
    ) {
        List<Menu> menus = menuService.getMenuList(status, menuName);
        return Response.ok(menus);
    }

    @PostMapping
    public Response<Object> postMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response<Menu> getMenuById(@PathVariable("id") Long id) {
        Menu menu = menuService.getMenuById(id);
        return Response.ok(menu);
    }

    @PutMapping
    public Response<Object> putMenu(@RequestBody Menu menu) {
        if (Objects.equals(menu.getParentId(), menu.getId())) {
            throw new SystemException(ResponseStatusEnum.SYSTEM_ERROR);
        }
        menuService.putMenu(menu);
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteById(@PathVariable("id") Long id) {
        menuService.deleteById(id);
        return Response.ok();
    }

    // @GetMapping("/tree")
    @GetMapping("/treeselect")
    public Response<List<Menu>> getMenuTree() {
        List<Menu> tree = menuService.getMenuTree();
        return Response.ok(tree);
    }

    // @GetMapping("/{id}/roleMenuTree")
    @GetMapping("/roleMenuTreeselect/{id}")
    public Response<Map<String, Object>> getRoleMenuTreeById(@PathVariable("id") Long id) {
        Map<String, Object> menus = menuService.getRoleMenuTreeById(id);
        return Response.ok(menus);
    }
}
