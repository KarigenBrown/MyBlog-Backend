package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.dto.ArticleDto;
import me.myblog.framework.domain.dto.TagListDto;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.domain.entity.Tag;
import me.myblog.framework.domain.vo.ArticleVo;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.service.FileService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
// @RequestMapping("/article")
public class ArticleController {
    @Autowired
    private FileService fileService;

    @Autowired
    private ArticleService articleService;

    // @PostMapping("/image")
    @PostMapping("/upload")
    public Response<String> postImage(@RequestPart("img") MultipartFile image) {
        String url = fileService.uploadPicture(image);
        return Response.ok(url);
    }

    @PostMapping("/content/article")
    public Response<Object> postArticle(@RequestBody ArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        List<Long> ids = articleDto.getTags().stream()
                .map(Long::parseLong)
                .toList();
        articleService.save(article, ids);
        return Response.ok();
    }

    @GetMapping("/content/article/list")
    public Response<PageVo> getArticleList(
            // @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("title") String title,
            @RequestParam("summary") String summary
    ) {
        List<Article> articleList = articleService.listPage(pageNum, pageSize, title, summary);
        PageVo data = new PageVo(articleList, Integer.toUnsignedLong(articleList.size()));
        return Response.ok(data);
    }

    @GetMapping("/content/article/{id}")
    public Response<ArticleVo> getArticleById(@PathVariable("id") Long id) {
        Article article = articleService.getArticleById(id);
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        List<String> tags = article.getTags().stream()
                .map(Tag::getId)
                .map(Objects::toString)
                .toList();
        articleVo.setTags(tags);
        return Response.ok(articleVo);
    }

    @PutMapping("/content/article")
    public Response<Object> putArticle(@RequestBody ArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        List<Long> ids = articleDto.getTags().stream()
                .map(Long::parseLong)
                .toList();
        articleService.save(article, ids);
        return Response.ok();
    }

    @DeleteMapping("/content/article/{id}")
    public Response<Object> deleteArticleById(@PathVariable("id") Long id) {
        articleService.deleteArticleById(id);
        return Response.ok();
    }
}
