package me.myblog.framework.utils;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityUtils {
    private SecurityUtils() {
    }

    public static User getLoginUser() {
        return (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
    }

    public static Long getUserId() {
        return getLoginUser().getId();
    }

    public static Boolean isAdmin() {
        return Objects.equals(SystemConstants.ADMINISTRATOR, SecurityUtils.getUserId());
    }
}
