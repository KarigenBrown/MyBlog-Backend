package me.myblog.framework.service.impl;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.jooq.tables.daos.CategoryDao;
import me.myblog.framework.domain.jooq.tables.pojos.Category;
import me.myblog.framework.service.CategoryService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.myblog.framework.domain.jooq.tables.Article.ARTICLE;
import static me.myblog.framework.domain.jooq.tables.Category.CATEGORY;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<Category> getAllCategory() {
        // 查询文章表，状态为已发布的文章
        /*List<Article> articles = dslContext.selectFrom(ARTICLE)
                .where(ARTICLE.STATUS.eq(SystemConstants.ARTICLE_STATUS_NORMAL))
                .fetchInto(Article.class);*/

        // 获取文章的分类id，并且去重
        /*Long[] categoryIds = articles.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet())
                .toArray(Long[]::new);*/

        // 查询分类表
        // List<Category> categories = categoryDao.fetchById(categoryIds);

        // 查询分类表
        return dslContext.selectFrom(CATEGORY)
                .where(CATEGORY.ID.in(
                        // 获取文章的分类id，并且去重
                        dslContext.selectDistinct(ARTICLE.CATEGORY_ID)
                                .from(ARTICLE)
                                // 查询文章表，状态为已发布的文章
                                .where(ARTICLE.STATUS.eq(SystemConstants.ARTICLE_STATUS_NORMAL))
                )).fetchInto(Category.class);
    }
}

