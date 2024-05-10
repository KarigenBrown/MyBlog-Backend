package me.myblog.guest.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.jooq.tables.pojos.Article;
import me.myblog.framework.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // @GetMapping("/hotArticles")
    @GetMapping("/hotArticleList")
    public Response<List<Article>> getHotArticles() {
        return Response.ok(articleService.getHotArticles());
    }
}
