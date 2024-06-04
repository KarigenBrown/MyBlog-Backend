package me.myblog.framework.service;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    // 查询热门文章，封装成List<Article>返回
    public List<Article> getHotArticles() {
        // 按照浏览量进行排序
        Sort sort = Sort.by(Sort.Direction.DESC, "viewCount");
        // 最多只查询10条
        PageRequest page = PageRequest.of(SystemConstants.HOT_ARTICLE_PAGE_NUMBER - 1, SystemConstants.HOT_ARTICLE_PAGE_SIZE, sort);

        Article article = new Article();
        // 必须是正式文章
        article.setStatus(SystemConstants.ARTICLE_STATUS_NORMAL);

        return articleRepository.findAll(Example.of(article), page).getContent();
    }

    public List<Article> getArticlesByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        // 对isTop进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, "isTop");
        // 分页查询
        PageRequest page = PageRequest.of(pageNum - 1, pageSize, sort);

        Article article = new Article();
        // 如果有categoryId就要查询，查询时要和传入的相同
        if (categoryId != null && categoryId > 0) {
            article.setCategoryId(categoryId);
        }
        article.setStatus(SystemConstants.ARTICLE_STATUS_NORMAL);
        return articleRepository.findAll(Example.of(article), page).getContent();
    }
}
