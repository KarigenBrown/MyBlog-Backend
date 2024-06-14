package me.myblog.framework.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user", schema = "myblog")
public class User implements UserDetails {
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    @Column(name = "create_by")
    @CreatedBy
    private Long createBy;

    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant createTime;

    @Column(name = "update_by")
    @LastModifiedBy
    private Long updateBy;

    @Column(name = "update_time")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant updateTime;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}