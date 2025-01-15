package me.myblog.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userName;
    private String nickName;
    private String password;
    private String email;
    private String phoneNumber;
    private Character sex;
    private Character status;
    private List<String> roleIds;
}
