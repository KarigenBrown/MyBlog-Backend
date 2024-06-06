package me.myblog.framework.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnore
    private List<Tag> tags;

}