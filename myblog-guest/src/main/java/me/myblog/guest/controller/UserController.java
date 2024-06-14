package me.myblog.guest.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// @RestController("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response<Map<String, Object>> login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            // 提示，必须要传用户名
            throw new SystemException(ResponseStatusEnum.REQUIRE_USERNAME);
        }
        Map<String, Object> result = userService.login(user);
        return Response.ok(result);
    }
}
