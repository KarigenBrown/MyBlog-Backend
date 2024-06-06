package me.myblog.framework.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@Entity
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