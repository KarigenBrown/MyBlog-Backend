package me.myblog.framework.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo {
    private Long id;
    private String menuName;
    private Long parentId;
    private Integer orderNum;
    private String path;
    private String component;
    private Integer isFrame;
    private Character menuType;
    private Character visible;
    private Character status;
    private String icon;
    private Instant createTime;
    private List<MenuVo> children;
}
