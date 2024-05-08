package me.myblog.framework.domain.entity;

import jakarta.persistence.*;
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
@Table(name = "article", schema = "myblog")
public class Article {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 256)
    @Column(name = "title", length = 256)
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Size(max = 1024)
    @Column(name = "summary", length = 1024)
    private String summary;

    @Column(name = "category_id")
    private Long categoryId;

    @Size(max = 256)
    @Column(name = "thumbnail", length = 256)
    private String thumbnail;

    @Column(name = "is_top")
    private Character isTop;

    @Column(name = "status")
    private Character status;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "is_comment")
    private Character isComment;

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
            name = "article_tag",
            joinColumns = {@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;

}