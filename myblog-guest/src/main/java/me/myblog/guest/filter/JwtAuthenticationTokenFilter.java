package me.myblog.guest.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.utils.BeanCopyUtils;
import me.myblog.framework.utils.JwtUtils;
import me.myblog.framework.utils.RedisCacheUtils;
import me.myblog.framework.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)) {
            // 解析获取userid
            String userid = JwtUtils.parseJWT(token).getSubject();
            // 从redis中获取用户信息
            Object object = redisCacheUtils.getCacheObject(SystemConstants.USER_LOGIN_KEY_PREFIX + userid);
            // todo 拷贝不成功
            // User user = BeanCopyUtils.copyBean(object, User.class);
            User user = objectMapper.readValue(objectMapper.writeValueAsString(object), User.class);
            // 如果获取不到
            if (user == null) {
                // 说明登录过期，提示重新登录
                WebUtils.renderString(response, objectMapper.writeValueAsString(Response.error(ResponseStatusEnum.NEED_LOGIN)));
            }
            // 存入SecurityContextHolder
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        // 说明该接口不需要登陆，直接放行
        filterChain.doFilter(request, response);
    }
}
