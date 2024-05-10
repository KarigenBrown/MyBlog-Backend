package me.myblog.framework.service;

import me.myblog.framework.domain.jooq.tables.pojos.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getHotArticles();
}

