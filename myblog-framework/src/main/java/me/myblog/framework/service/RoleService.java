package me.myblog.framework.service;

import jakarta.persistence.criteria.Predicate;
import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.Role_;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.repository.RoleRepository;
import me.myblog.framework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<String> selectRoleKeyByUserId(Long id) {
        // 判断是否是管理员，如果是返回集合中只需要有admin
        if (id == 1L) {
            return List.of("admin");
        }
        // 否则查询用户所具有的角色信息
        // todo 改为sql查询
        User user = userRepository.findById(id).get();
        return user.getRoles().stream()
                .filter(role -> role.getStatus().equals(SystemConstants.NORMAL_ROLE_STATUS.charAt(0)))
                .map(Role::getRoleKey)
                .distinct()
                .toList();
    }

    public List<Role> getRoleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        List<Role> roles = roleRepository.findAll(((root, query, builder) -> {
            Predicate r = builder.like(root.get(Role_.ROLE_NAME), roleName);
            Predicate s = builder.equal(root.get(Role_.STATUS), status);
            query.where(builder.and(r, s));
            return query.getRestriction();
        }));
        return roles.stream()
                .sorted(Comparator.comparing(Role::getRoleSort))
                .toList();
    }

    public void putRoleStatus(Role role) {
        Role record = roleRepository.getReferenceById(role.getId());
        record.setStatus(role.getStatus());
        roleRepository.saveAndFlush(record);
    }
}
