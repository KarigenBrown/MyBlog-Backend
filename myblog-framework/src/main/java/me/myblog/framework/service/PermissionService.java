package me.myblog.framework.service;

import me.myblog.framework.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    /**
     * 判断当前用户是否具有permission
     *
     * @param permission 要判断的权限
     * @return
     */
    public boolean hasPermission(String permission) {
        // 如果是超级管理员，直接返回
        if (SecurityUtils.isAdmin()) {
            return true;
        }
        // 否则获取档期那登录用户所具有的权限列表，判断是否具有permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
