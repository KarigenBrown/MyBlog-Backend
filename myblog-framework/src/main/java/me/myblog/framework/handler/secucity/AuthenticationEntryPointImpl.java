package me.myblog.framework.handler.secucity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.myblog.framework.domain.Response;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        Response<String> error = switch (authException) {
            case BadCredentialsException e -> Response.error(ResponseStatusEnum.LOGIN_ERROR.getCode(), e.getMessage());
            case InsufficientAuthenticationException e ->
                    Response.error(ResponseStatusEnum.NEED_LOGIN.getCode(), e.getMessage());
            default -> Response.error(ResponseStatusEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");
        };
        // 响应给前端
        WebUtils.renderString(response, objectMapper.writeValueAsString(error));
    }
}
