package me.myblog.framework.service;

import jakarta.persistence.criteria.Predicate;
import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Menu;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.Role_;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.repository.MenuRepository;
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

    @Autowired
    private MenuRepository menuRepository;

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

    public void postRole(Role role, List<Long> menuIds) {
        List<Menu> menus = menuRepository.findAllById(menuIds);
        role.setMenus(menus);
        roleRepository.saveAndFlush(role);
    }

    public Role getById(Long id) {
        return roleRepository.getReferenceById(id);
    }

    public void putRole(Role role, List<Long> ids) {
        List<Menu> menus = menuRepository.findAllById(ids);
        role.setMenus(menus);
        roleRepository.saveAndFlush(role);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
