package me.myblog.guest.job;

import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ViewCountJob {
    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 */10 * * * ?")
    public void updateViewCount() {
        // 获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCacheUtils.getCacheMap(SystemConstants.VIEW_COUNT_KEY);
        List<Article> articles = viewCountMap.entrySet().stream()
                .map(entry -> {
                    Article article = new Article();
                    article.setId(Long.valueOf(entry.getKey()));
                    article.setViewCount(entry.getValue().longValue());
                    return article;
                }).toList();
        // 更新到数据库中
        articleService.updateBatchViewCountById(articles);
    }
}
