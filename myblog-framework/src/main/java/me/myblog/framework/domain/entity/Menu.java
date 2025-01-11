package me.myblog.framework.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Table(name = "menu", schema = "myblog")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "menu_name", nullable = false, length = 50)
    private String menuName;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "order_num")
    private Integer orderNum;

    @Size(max = 200)
    @Column(name = "path", length = 200)
    private String path;

    @Size(max = 255)
    @Column(name = "component")
    private String component;

    @Column(name = "is_frame")
    private Integer isFrame;

    @Column(name = "menu_type")
    private Character menuType;

    @Column(name = "visible")
    private Character visible;

    @Column(name = "status")
    private Character status;

    @Size(max = 100)
    @Column(name = "perms", length = 100)
    private String perms;

    @Size(max = 100)
    @Column(name = "icon", length = 100)
    private String icon;

    @Column(name = "create_by")
    @CreatedBy
    private Long createBy;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Instant createTime;

    @Column(name = "update_by")
    @LastModifiedBy
    private Long updateBy;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Instant updateTime;

    @Size(max = 500)
    @Column(name = "remark", length = 500)
    private String remark;

    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private List<Role> roles;

    // 已经关联出来了，但是需要更多过滤条件
    // @OneToMany(mappedBy = "parentId")
    @Transient
    private List<Menu> children;
}