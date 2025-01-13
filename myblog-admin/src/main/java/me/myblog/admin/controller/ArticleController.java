package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.dto.AddArticleDto;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.service.FileService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public Response<Object> postArticle(@RequestBody AddArticleDto addArticleDto) {
        Article article = BeanCopyUtils.copyBean(addArticleDto, Article.class);
        articleService.save(article);
        return Response.ok();
    }
}
