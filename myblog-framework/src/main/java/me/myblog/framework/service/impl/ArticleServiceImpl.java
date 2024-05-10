package me.myblog.framework.service.impl;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.jooq.tables.daos.ArticleDao;
import me.myblog.framework.domain.jooq.tables.pojos.Article;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.utils.WebUtils;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.myblog.framework.domain.jooq.tables.Article.ARTICLE;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private DSLContext dslContext;

    // 查询热门文章，封装成List<Article>返回
    @Override
    public List<Article> getHotArticles() {
        return dslContext.selectFrom(ARTICLE)
                // 必须是正式文章
                .where(ARTICLE.STATUS.eq(SystemConstants.ARTICLE_STATUS_NORMAL))
                // 按照浏览量进行排序
                .orderBy(ARTICLE.VIEW_COUNT.desc())
                // 最多只查询10条
                .limit(SystemConstants.HOT_ARTICLE_PAGE_SIZE)
                .offset(WebUtils.calculatePageOffset(SystemConstants.HOT_ARTICLE_PAGE_NUMBER, SystemConstants.HOT_ARTICLE_PAGE_SIZE))
                .fetchInto(Article.class);
    }
}

