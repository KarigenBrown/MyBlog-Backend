package me.myblog.framework.handler.auditing;

import me.myblog.framework.utils.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Long userId = -1L;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception exception) {
            // 表示是自己创建的
            exception.printStackTrace();
        }
        return Optional.of(userId);
    }
}
