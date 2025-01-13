package me.myblog.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.myblog.framework.domain.entity.Tag;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleDto {
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
    private List<Tag> tags;
}
