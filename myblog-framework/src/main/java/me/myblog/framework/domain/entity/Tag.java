package me.myblog.framework.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "tag", schema = "myblog")
public class Tag {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @Column(name = "name", length = 128)
    private String name;

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

    @Size(max = 500)
    @Column(name = "remark", length = 500)
    private String remark;

    @ManyToMany(mappedBy = "tags")
    private List<Article> articles;

}