package me.myblog.framework.service;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.domain.meta.Article_;
import me.myblog.framework.repository.ArticleRepository;
import me.myblog.framework.utils.RedisCacheUtils;
import me.myblog.framework.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    // 查询热门文章，封装成List<Article>返回
    public List<Article> getHotArticles() {
        // 按照浏览量进行排序
        Sort sort = Sort.by(Sort.Direction.DESC, Article_.VIEW_COUNT);
        // 最多只查询10条
        PageRequest page = PageRequest.of(WebUtils.toJpaPageNumber(SystemConstants.HOT_ARTICLE_PAGE_NUMBER), SystemConstants.HOT_ARTICLE_PAGE_SIZE, sort);

        Article article = new Article();
        // 必须是正式文章
        article.setStatus(SystemConstants.ARTICLE_STATUS_NORMAL);

        return articleRepository.findAll(Example.of(article), page).getContent();
    }

    public List<Article> getArticlesByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        // 对isTop进行降序
        Sort sort = Sort.by(Sort.Direction.DESC, Article_.IS_TOP);
        // 分页查询
        PageRequest page = PageRequest.of(WebUtils.toJpaPageNumber(pageNum), pageSize, sort);

        Article article = new Article();
        // 如果有categoryId就要查询，查询时要和传入的相同
        if (categoryId != null && categoryId > 0) {
            article.setCategoryId(categoryId);
        }
        article.setStatus(SystemConstants.ARTICLE_STATUS_NORMAL);
        return articleRepository.findAll(Example.of(article), page).getContent();
    }

    public Article getArticleById(Long id) {
        // 根据id查询文章
        Article article = articleRepository.getReferenceById(id);
        // 从redis中获取viewCount
        Integer viewCount = redisCacheUtils.getCacheMapValue(SystemConstants.VIEW_COUNT_KEY, id.toString());
        article.setViewCount(viewCount.longValue());
        return article;
    }

    public List<Article> list() {
        return articleRepository.findAll();
    }

    public void updateBatchViewCountById(List<Article> articles) {
        Map<Long, Article> records = articleRepository.findAll().stream().collect(Collectors.toMap(
                Article::getId,
                Function.identity()
        ));
        for (Article article : articles) {
            Article record = records.get(article.getId());
            record.setViewCount(article.getViewCount());
            articleRepository.save(record);
        }
        articleRepository.flush();
    }

    public void putViewCount(Long id) {
        // 更新redis中对应id的浏览量
        redisCacheUtils.incrementCacheMapValue(SystemConstants.VIEW_COUNT_KEY, id.toString(), 1);
    }
}
