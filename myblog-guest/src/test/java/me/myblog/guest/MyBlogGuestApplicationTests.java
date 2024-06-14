package me.myblog.guest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.repository.UserRepository;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.utils.BeanCopyUtils;
import me.myblog.framework.utils.RedisCache;
import me.myblog.framework.utils.RedisCacheUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = MyBlogGuestApplication.class)
public class MyBlogGuestApplicationTests {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Autowired
    private RedisCache<User> redisCache;

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

    @Test
    @Transactional(readOnly = true)
    public void testJackson() throws JsonProcessingException {
        User user = userRepository.getReferenceById(1L);
        //redisCacheUtils.setCacheObject("1", user);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        System.out.println(objectMapper.writeValueAsString(BeanCopyUtils.copyBean(user, User.class)));
    }
}
