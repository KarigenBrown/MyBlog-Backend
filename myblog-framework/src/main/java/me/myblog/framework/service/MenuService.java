package me.myblog.framework.service;

import jakarta.persistence.criteria.Predicate;
import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Menu;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.domain.meta.Menu_;
import me.myblog.framework.domain.meta.Role_;
import me.myblog.framework.domain.meta.User_;
import me.myblog.framework.repository.MenuRepository;
import me.myblog.framework.repository.RoleRepository;
import me.myblog.framework.repository.UserRepository;
import me.myblog.framework.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<String> selectPermsByUserId(Long id) {
        // 如果是管理员，返回所有权限
        if (SecurityUtils.isAdmin()) {
            List<Menu> menus = menuRepository.findAll((root, query, builder) -> {
                Predicate menuType = root.get(Menu_.MENU_TYPE).in(SystemConstants.MENU, SystemConstants.BUTTON);
                Predicate menuStatus = builder.equal(root.get(Menu_.STATUS), SystemConstants.MENU_STATUS_NORMAL);

                query.where(builder.and(menuType, menuStatus));
                return query.getRestriction();
            });

            return menus.stream()
                    .map(Menu::getPerms)
                    .distinct()
                    .toList();
        }
        // 否则返回其所具有的权限
        // todo 改为sql查询
        User admin = userRepository.findById(id).get();
        List<Character> range = List.of(SystemConstants.MENU.charAt(0), SystemConstants.BUTTON.charAt(0));
        return admin.getRoles().stream()
                .flatMap(role -> role.getMenus().stream())
                .filter(menu -> menu.getStatus().equals(SystemConstants.MENU_STATUS_NORMAL.charAt(0)))
                .filter(menu -> range.contains(menu.getMenuType()))
                .map(Menu::getPerms)
                .distinct()
                .toList();
    }

    public List<Menu> selectRoutesMenuTreeByUserId(Long adminId) {
        List<Menu> menus = null;
        List<Character> range = List.of(SystemConstants.MENU.charAt(0), SystemConstants.FOLDER.charAt(0));
        // 判断是否是管理员
        // todo 改为sql查询
        if (SecurityUtils.isAdmin()) {
            // 如果是返回所有符合要求的Menu
            menus = menuRepository.findAll().stream()
                    .filter(menu -> menu.getStatus().equals(SystemConstants.MENU_STATUS_NORMAL.charAt(0)))
                    .filter(menu -> range.contains(menu.getMenuType()))
                    .distinct()
                    .sorted(Comparator.comparing(Menu::getParentId).thenComparing(Menu::getOrderNum))
                    .toList();
        } else {
            // 否则当前用户所具有的Menu
            User admin = userRepository.findById(adminId).get();
            menus = admin.getRoles().stream()
                    .flatMap(role -> role.getMenus().stream())
                    .filter(menu -> menu.getStatus().equals(SystemConstants.MENU_STATUS_NORMAL.charAt(0)))
                    .filter(menu -> range.contains(menu.getMenuType()))
                    .distinct()
                    .sorted(Comparator.comparing(Menu::getParentId).thenComparing(Menu::getOrderNum))
                    .toList();
        }
        // 构建tree
        // 现找出第一层的菜单，然后找出他们的子菜单设置到children属性中
        return buildMenuTree(menus, SystemConstants.MENU_TREE_ROOT);
    }

    private List<Menu> buildMenuTree(List<Menu> menus, Long parentId) {
        return menus.stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
                .peek(menu -> menu.setChildren(getChildren(menu, menus)))
                .toList();
    }

    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        return menus.stream()
                .filter(m -> menu.getId().equals(m.getParentId()))
                .peek(m -> m.setChildren(getChildren(m, menus)))
                .toList();
    }

    public List<Menu> getMenuList(String status, String menuName) {
        List<Menu> menus = menuRepository.findAll((root, query, builder) -> {
            Predicate s = builder.like(root.get(Menu_.STATUS), status);
            Predicate n = builder.like(root.get(Menu_.MENU_NAME), menuName);
            query.where(builder.or(s, n));
            return query.getRestriction();
        });
        return menus.stream()
                .sorted(Comparator.comparing(Menu::getParentId).thenComparing(Menu::getOrderNum))
                .toList();
    }

    public void save(Menu menu) {
        menuRepository.saveAndFlush(menu);
    }

    public Menu getMenuById(Long id) {
        return menuRepository.getReferenceById(id);
    }

    public void putMenu(Menu menu) {
        menuRepository.saveAndFlush(menu);
    }

    public void deleteById(Long id) {
        menuRepository.deleteById(id);
    }
}
