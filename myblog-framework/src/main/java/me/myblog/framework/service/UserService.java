package me.myblog.framework.service;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.repository.UserRepository;
import me.myblog.framework.utils.BeanCopyUtils;
import me.myblog.framework.utils.JwtUtils;
import me.myblog.framework.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询信息
        User queryUser = new User();
        queryUser.setUserName(username);
        Optional<User> optionalUser = userRepository.findOne(Example.of(queryUser));

        // 判断是否查到用户，如果没查到抛出异常
        optionalUser.orElseThrow(() -> new RuntimeException("用户不存在"));

        // 返回用户信息
        // todo 查询权限信息封装
        return optionalUser.get();
    }

    // 登录请求进来先调login，然后通过AuthenticationManager调loadUserByUsername
    public Map<String, Object> login(User queryUser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(queryUser.getUsername(), queryUser.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 判断是否认证通过
        Optional.ofNullable(authentication).orElseThrow(() -> new RuntimeException("用户名或密码错误"));

        // 获取userid生成token
        User user = (User) authentication.getPrincipal();
        String userid = user.getId().toString();
        String jwt = JwtUtils.createJWT(userid);

        // 把用户信息存入redis
        redisCacheUtils.setCacheObject(SystemConstants.USER_LOGIN_KEY_PREFIX + userid, BeanCopyUtils.copyBean(user, User.class));

        // 把token和用户信息封装返回
        return Map.of("token", jwt, "userInfo", user);
    }
}
