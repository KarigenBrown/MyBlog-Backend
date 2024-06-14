package me.myblog.framework.handler.secucity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.myblog.framework.domain.Response;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        Response<String> error = Response.error(ResponseStatusEnum.NO_OPERATOR_AUTH);
        // 相应给前端
        WebUtils.renderString(response, objectMapper.writeValueAsString(error));
    }
}
