package me.myblog.guest;

import me.myblog.framework.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyBlogGuestApplication.class)
public class MyBlogGuestApplicationTests {
    @Autowired
    private ArticleService articleService;

    @Test
    public void testGetHotArticles() {
        System.out.println("articleService.getHotArticles() = " + articleService.getHotArticles());
    }
}
