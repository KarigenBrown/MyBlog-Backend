package me.myblog.guest;

import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = MyBlogGuestApplication.class)
public class MyBlogGuestApplicationTests {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Transactional(readOnly = true)
    public void testRepository() {
        Article article = articleRepository.findById(1L).get();
        System.out.println(article.getTags().get(0).getRemark());
    }

}
