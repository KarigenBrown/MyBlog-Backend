package me.myblog.framework.service;

import me.myblog.framework.domain.jooq.tables.pojos.Article;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2024-05-09 22:00:03
 */
public interface ArticleService {
    List<Article> getHotArticles();
}

