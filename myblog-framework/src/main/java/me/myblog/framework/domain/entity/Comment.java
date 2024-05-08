package me.myblog.framework.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@Table(name = "comment", schema = "myblog")
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type")
    private Character type;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "root_id")
    private Long rootId;

    @Size(max = 512)
    @Column(name = "content", length = 512)
    private String content;

    @Column(name = "to_comment_user_id")
    private Long toCommentUserId;

    @Column(name = "to_comment_id")
    private Long toCommentId;

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

}