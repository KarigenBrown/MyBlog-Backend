package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.dto.AddArticleDto;
import me.myblog.framework.domain.dto.TagListDto;
import me.myblog.framework.domain.entity.Article;
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
    public Response<Article> getArticleById(@PathVariable("id") Long id) {
        Article article = articleService.getArticleById(id);
        return Response.ok(article);
    }

    @PutMapping("/content/article")
    public Response<Object> putArticle(@RequestBody Article article){
        articleService.save(article);
        return Response.ok();
    }

    @DeleteMapping("/content/article/{id}")
    public Response<Object> deleteArticleById(@PathVariable("id") Long id){
        articleService.deleteArticleById(id);
        return Response.ok();
    }
}
