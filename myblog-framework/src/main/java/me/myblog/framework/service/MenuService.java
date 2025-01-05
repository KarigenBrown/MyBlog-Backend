package me.myblog.framework.service;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.meta.Menu_;
import me.myblog.framework.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<String> selectPermsByUserId(Long id) {
        // 如果是管理员，返回所有权限
        if (id == 1L) {
            menuRepository.findAll((root, query, builder) -> {
                return null;
            });
        }
        // 否则返回其所具有的权限
        return null;
    }
}
