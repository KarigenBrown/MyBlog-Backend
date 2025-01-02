package me.myblog.guest.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.domain.entity.Category;
import me.myblog.framework.domain.vo.ArticleDetailVo;
import me.myblog.framework.domain.vo.ArticleListVo;
import me.myblog.framework.domain.vo.HotArticleVo;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.service.CategoryService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Lazy
    @Autowired
    private CategoryService categoryService;

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
            @RequestParam("pageSize") Integer pageSize
    ) {
        List<Article> articles = articleService.getArticlesByCategoryId(categoryId, pageNum, pageSize);
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        Map<Long, String> categories = categoryService.getAllCategory()
                .stream()
                .collect(Collectors.toMap(Category::getId, Category::getName));
        articleListVos.forEach(articleListVo -> articleListVo.setCategoryName(categories.get(articleListVo.getCategoryId())));
        PageVo pageVo = new PageVo(articleListVos, Integer.toUnsignedLong(articleListVos.size()));
        return Response.ok(pageVo);
    }

    @GetMapping("/{id}")
    public Response<ArticleDetailVo> getArticleById(@PathVariable("id") Long id) {
        Article article = articleService.getArticleById(id);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Category category = categoryService.getCategoryById(articleDetailVo.getCategoryId());
        articleDetailVo.setCategoryName(category.getName());
        return Response.ok(articleDetailVo);
    }

    // @PutMapping("/viewCount/{id}")
    @PutMapping("/updateViewCount/{id}")
    public Response<Object> putViewCount(@PathVariable("id") Long id) {
        articleService.putViewCount(id);
        return Response.ok();
    }
}
