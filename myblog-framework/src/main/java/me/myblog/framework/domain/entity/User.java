package me.myblog.framework.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "user", schema = "myblog")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 64)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 64)
    private String userName;

    @Size(max = 64)
    @NotNull
    @Column(name = "nick_name", nullable = false, length = 64)
    private String nickName;

    @Size(max = 64)
    @NotNull
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "type")
    private Character type;

    @Column(name = "status")
    private Character status;

    @Size(max = 64)
    @Column(name = "email", length = 64)
    private String email;

    @Size(max = 32)
    @Column(name = "phonenumber", length = 32)
    private String phonenumber;

    @Column(name = "sex")
    private Character sex;

    @Size(max = 128)
    @Column(name = "avatar", length = 128)
    private String avatar;

    @CreatedBy
    @Column(name = "create_by")
    private Long createBy;

    @CreatedDate
    @Column(name = "create_time")
    private Instant createTime;

    @LastModifiedBy
    @Column(name = "update_by")
    private Long updateBy;

    @LastModifiedDate
    @Column(name = "update_time")
    private Instant updateTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<Role> roles;

}