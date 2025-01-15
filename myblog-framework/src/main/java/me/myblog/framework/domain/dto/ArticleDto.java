package me.myblog.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String summary;
    private Long categoryId;
    private String thumbnail;
    private Character isTop;
    private Character status;
    private Long viewCount;
    private Character isComment;
    private Long createBy;
    private Instant createTime;
    private Long updateBy;
    private Instant updateTime;
    private List<String> tags;
}
