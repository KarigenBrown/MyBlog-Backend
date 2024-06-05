package me.myblog.framework.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo {
    private Long id;
    private String title;
    private String summary;
    @JsonIgnore
    private Long categoryId;
    private String categoryName;
    private String thumbnail;
    private Long viewCount;
    private LocalDateTime createTime;
}
