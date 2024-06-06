package me.myblog.guest;

import me.myblog.framework.domain.entity.Menu;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.repository.UserRepository;
import me.myblog.framework.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = MyBlogGuestApplication.class)
public class MyBlogGuestApplicationTests {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional(readOnly = true)
    public void testGetHotArticles() {
        System.out.println("articleService.getHotArticles() = " + articleService.getHotArticles());
    }

    @Test
    @Transactional(readOnly = true)
    public void testJoin() {
        User user = userRepository.getReferenceById(1L);
        List<Role> roles = user.getRoles();
        System.out.println("roles.getFirst().getRoleName() = " + roles.getFirst().getRoleName());
    }
}
