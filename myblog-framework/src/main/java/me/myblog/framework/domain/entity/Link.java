package me.myblog.framework.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "link", schema = "myblog")
public class Link {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 256)
    @Column(name = "name", length = 256)
    private String name;

    @Size(max = 256)
    @Column(name = "logo", length = 256)
    private String logo;

    @Size(max = 512)
    @Column(name = "description", length = 512)
    private String description;

    @Size(max = 128)
    @Column(name = "address", length = 128)
    private String address;

    @Column(name = "status")
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

}