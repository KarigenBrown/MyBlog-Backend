package me.myblog.guest.controller;

import me.myblog.framework.annotation.SystemLog;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.domain.vo.UserInfoVo;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.service.FileService;
import me.myblog.framework.service.UserService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

// @RestController("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @SystemLog(businessName = "用户登录")
    @PostMapping("/login")
    public Response<Map<String, Object>> loginUser(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            // 提示，必须要传用户名
            throw new SystemException(ResponseStatusEnum.REQUIRE_USERNAME);
        }
        Map<String, Object> result = userService.loginUser(user);
        return Response.ok(result);
    }

    // @DeleteMapping("/logout")
    @PostMapping("/logout")
    public Response<Object> logoutUser() {
        userService.logoutUser();
        return Response.ok();
    }

    // @GetMapping("/userInformation")
    @GetMapping("/user/userInfo")
    public Response<UserInfoVo> getUserInformation() {
        User user = userService.getUserInformation();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return Response.ok(userInfoVo);
    }

    // @PostMapping("/userPhoto")
    @PostMapping("/upload")
    public Response<String> putUserPhoto(@RequestPart("img") MultipartFile photo) {
        String url = fileService.uploadPicture(photo);
        return Response.ok(url);
    }

    @SystemLog(businessName = "更新用户信息")
    // @PutMapping("/userInformation")
    @PutMapping("/user/userInfo")
    public Response<Object> putUserInformation(@RequestBody User user) {
        userService.putUserInformation(user);
        return Response.ok();
    }

    // @PostMapping("/register")
    @PostMapping("/user/register")
    public Response<Object> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return Response.ok();
    }
}
