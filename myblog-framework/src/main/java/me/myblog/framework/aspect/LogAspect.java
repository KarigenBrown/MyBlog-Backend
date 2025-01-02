package me.myblog.framework.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.myblog.framework.annotation.SystemLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(me.myblog.framework.annotation.SystemLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    @SneakyThrows
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            proceedBefore(joinPoint);
            result = joinPoint.proceed();
            proceedAfter(result);
        } finally {
            // 结束后换行
            log.info("========== End ==========" + System.lineSeparator());
        }

        return result;
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(SystemLog.class);
    }

    @SneakyThrows
    private void proceedBefore(ProceedingJoinPoint joinPoint) {
        log.info("========= Start =========" + System.lineSeparator());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();

        // 获取被增强方法上的注解对象
        SystemLog systemLog = getSystemLog(joinPoint);

        // 打印请求URL
        log.info("URL                     : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("BusinessName            : {}", systemLog.businessName());
        // 打印HTTP Method
        log.info("HTTP Method             : {}", request.getMethod());
        // 打印调用controller的全路径以及执行方法
        log.info("Class Method            : {}#{}()",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
        // 打印请求的IP
        log.info("IP                      : {}", request.getRemoteHost());
        // 打印请求的参数
        log.info("Request Parameters      : {}", objectMapper.writeValueAsString(joinPoint.getArgs()));
    }

    @SneakyThrows
    private void proceedAfter(Object result) {
        // 打印响应参数
        log.info("Response                : {}", objectMapper.writeValueAsString(result));
    }
}
