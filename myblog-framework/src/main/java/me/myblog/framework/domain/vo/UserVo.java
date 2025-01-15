package me.myblog.framework.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.myblog.framework.domain.dto.UserDetailVo;
import me.myblog.framework.domain.entity.Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private UserDetailVo userDetail;
    private List<Role> roles;
    private List<String> roleIds;
}
