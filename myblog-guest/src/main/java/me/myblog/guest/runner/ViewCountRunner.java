package me.myblog.guest.runner;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Override
    public void run(String... args) throws Exception {
        // 查询博客信息,id和viewCount
        List<Article> articles = articleService.list();
        Map<String, Long> viewCountMap = articles.stream()
                .collect(Collectors.toMap(
                        article -> article.getId().toString(),
                        Article::getViewCount
                ));
        // 存储到redis中
        redisCacheUtils.setCacheMap(SystemConstants.VIEW_COUNT_KEY, viewCountMap);
    }
}
