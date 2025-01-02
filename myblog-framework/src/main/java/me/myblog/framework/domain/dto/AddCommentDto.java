package me.myblog.framework.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "添加评论dto")
public class AddCommentDto {
    @Schema(description = "文章id")
    private Long id;
    @Schema(description = "评论类型（0代表文章评论，1代表友链评论）")
    private Character type;
    private Long articleId;
    private Long rootId;
    private String content;
    private Long toCommentUserId;
    private Long toCommentId;
    private Long createBy;
    private Instant createTime;
    private Long updateBy;
    private Instant updateTime;
}
