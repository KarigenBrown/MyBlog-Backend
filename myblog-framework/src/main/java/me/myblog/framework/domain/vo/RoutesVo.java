package me.myblog.framework.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.myblog.framework.domain.entity.Menu;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutesVo {
    private List<Menu> menus;
}
