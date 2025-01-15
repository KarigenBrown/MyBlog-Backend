package me.myblog.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailVo {
    private String userName;
    private String nickName;
    private String password;
    private String email;
    private String phoneNumber;
    private Character sex;
    private Character status;
}
