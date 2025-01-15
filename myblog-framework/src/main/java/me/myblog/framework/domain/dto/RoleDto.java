package me.myblog.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private Character status;
    private String remark;
    private List<String> menuIds;
}
