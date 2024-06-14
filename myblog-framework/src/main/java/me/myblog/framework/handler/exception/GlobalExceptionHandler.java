package me.myblog.framework.handler.exception;

import lombok.extern.slf4j.Slf4j;
import me.myblog.framework.domain.Response;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public Response<String> systemExceptionHandler(SystemException e) {
        // 打印异常信息
        log.error("异常: ", e);
        // 从异常对象中获取提示信息封装返回
        return Response.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response<String> exceptionHandler(Exception e) {
        // 打印异常信息
        log.error("异常: ", e);
        // 从异常对象中获取提示信息封装返回
        return Response.error(ResponseStatusEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
