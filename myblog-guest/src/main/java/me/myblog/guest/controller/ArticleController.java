package me.myblog.guest.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.jooq.tables.pojos.Article;
import me.myblog.framework.domain.vo.ArticleVo;
import me.myblog.framework.domain.vo.HotArticleVo;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    // @GetMapping("/hotArticles")
    @GetMapping("/hotArticleList")
    public Response<List<HotArticleVo>> getHotArticles() {
        List<Article> hotArticles = articleService.getHotArticles();
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(hotArticles, HotArticleVo.class);
        return Response.ok(hotArticleVos);
    }

    // @GetMapping("/{categoryId}/{pageNumber}/{pageSize}")
    @GetMapping("/articleList")
    public Response<PageVo> getArticlesByCategoryId(
            // @PathVariable("categoryId") Long categoryId,
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            // @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            // @PathVariable("pageSize") Integer pageSize
            @RequestParam("pageSize") Integer pageSize) {
        List<Article> articles = articleService.getArticlesByCategoryId(categoryId, pageNum, pageSize);
        List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, ArticleVo.class);
        PageVo pageVo = new PageVo(articleVos, Integer.toUnsignedLong(articleVos.size()));
        return Response.ok(pageVo);
    }
}
