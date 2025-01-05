package me.myblog.framework.service;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.repository.UserRepository;
import me.myblog.framework.utils.BeanCopyUtils;
import me.myblog.framework.utils.JwtUtils;
import me.myblog.framework.utils.RedisCacheUtils;
import me.myblog.framework.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public Map<String, Object> loginUser(User queryUser) {
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

    public void logoutUser() {
        String userid = SecurityUtils.getUserId().toString();
        redisCacheUtils.deleteCacheObject(SystemConstants.USER_LOGIN_KEY_PREFIX + userid);
    }

    public User getById(Long createBy) {
        return userRepository.getReferenceById(createBy);
    }

    public User getUserInformation() {
        Long userId = SecurityUtils.getUserId();
        return userRepository.getReferenceById(userId);
    }

    public void putUserInformation(User user) {
        User record = userRepository.getReferenceById(user.getId());
        record.setAvatar(user.getAvatar());
        record.setEmail(user.getEmail());
        record.setNickName(user.getNickName());
        record.setSex(user.getSex());
        userRepository.saveAndFlush(record);
    }

    private boolean userNameExist(String userName) {
        User user = new User();
        user.setUserName(userName);
        return userRepository.count(Example.of(user)) > 0;
    }

    private boolean nickNameExist(String nickName) {
        User user = new User();
        user.setNickName(nickName);
        return userRepository.count(Example.of(user)) > 0;
    }

    public void registerUser(User user) {
        // 对数据进行非空判断
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(ResponseStatusEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(ResponseStatusEnum.NICKNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(ResponseStatusEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(ResponseStatusEnum.EMAIL_NOT_NULL);
        }

        // 对数据进行是否存在的判断
        if (userNameExist(user.getUsername())) {
            throw new SystemException(ResponseStatusEnum.USERNAME_EXIST);
        }
        if (nickNameExist(user.getNickName())) {
            throw new SystemException(ResponseStatusEnum.NICKNAME_EXIST);
        }
        // ...

        // 对密码进行加密
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);

        // 存入数据库
        userRepository.saveAndFlush(user);
    }

    public Map<String, String> loginAdmin(User queryAdmin) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(queryAdmin.getUsername(), queryAdmin.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 判断是否认证通过
        Optional.ofNullable(authentication).orElseThrow(() -> new RuntimeException("用户名或密码错误"));

        // 获取userid生成token
        User admin = (User) authentication.getPrincipal();
        String userid = admin.getId().toString();
        String jwt = JwtUtils.createJWT(userid);

        // 把用户信息存入redis
        redisCacheUtils.setCacheObject(SystemConstants.ADMIN_LOGIN_KEY_PREFIX + userid, BeanCopyUtils.copyBean(admin, User.class));

        // 把token和用户信息封装返回
        return Map.of("token", jwt);
    }
}
