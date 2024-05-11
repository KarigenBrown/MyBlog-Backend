package me.myblog.framework.service.impl;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.jooq.tables.daos.ArticleDao;
import me.myblog.framework.domain.jooq.tables.pojos.Article;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.utils.WebUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.True;
import org.jooq.impl.DSL;
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

    @Override
    public List<Article> getArticlesByCategoryId(Long categoryId, Integer pageNumber, Integer pageSize) {
        Condition condition = DSL.trueCondition();
        // 如果有categoryId就要查询，查询时要和传入的相同
        if (categoryId != null && categoryId > 0) {
            condition = condition.and(ARTICLE.CATEGORY_ID.eq(categoryId));
        }
        // 状态是正式发布的
        condition = condition.and(ARTICLE.STATUS.eq(SystemConstants.ARTICLE_STATUS_NORMAL));

        return dslContext.selectFrom(ARTICLE)
                .where(condition)
                // 对isTop进行降序
                .orderBy(ARTICLE.IS_TOP.desc())
                // 分页查询
                .limit(pageSize)
                .offset(WebUtils.calculatePageOffset(pageNumber, pageSize))
                .fetchInto(Article.class);
    }
}

