package me.myblog.framework.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private Long id;
    private Long articleId;
    private Long rootId;
    private String content;
    private Long toCommentUserId;
    private String toCommentUserName;
    private Long toCommentId;
    private Long createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant createTime;

    private String username;
    private List<CommentVo> children;
}
