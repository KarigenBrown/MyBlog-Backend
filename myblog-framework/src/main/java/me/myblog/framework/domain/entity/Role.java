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
@Table(name = "role", schema = "myblog")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "role_name", nullable = false, length = 30)
    private String roleName;

    @Size(max = 100)
    @NotNull
    @Column(name = "role_key", nullable = false, length = 100)
    private String roleKey;

    @NotNull
    @Column(name = "role_sort", nullable = false)
    private Integer roleSort;

    @NotNull
    @Column(name = "status", nullable = false)
    private Character status;

    @Column(name = "create_by")
    @CreatedBy
    private Long createBy;

    @Column(name = "create_time")
    @CreatedDate
    private Instant createTime;

    @Column(name = "update_by")
    @LastModifiedBy
    private Long updateBy;

    @Column(name = "update_time")
    @LastModifiedDate
    private Instant updateTime;

    @Size(max = 500)
    @Column(name = "remark", length = 500)
    private String remark;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "role_menu",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private List<Menu> menus;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}