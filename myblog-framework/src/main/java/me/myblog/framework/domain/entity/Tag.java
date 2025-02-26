package me.myblog.framework.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "tag", schema = "myblog")
@EntityListeners(AuditingEntityListener.class)
public class Tag {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 128)
    @Column(name = "name", length = 128)
    private String name;

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

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Article> articles;

}