package me.myblog.guest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Role;
import me.myblog.framework.domain.entity.User;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.repository.UserRepository;
import me.myblog.framework.service.ArticleService;
import me.myblog.framework.utils.BeanCopyUtils;
import me.myblog.framework.utils.RedisCacheUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

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
        PageVo pageVo = new PageVo(List.of(1, 2, 3, 4), 4L);
        redisCacheUtils.setCacheObject("1", pageVo);
        PageVo pageVo2 = redisCacheUtils.getCacheObject("1");
        System.out.println("pageVo2 = " + pageVo2);
    }

    @Test
    public void testPassword() {
        System.out.println(passwordEncoder.matches("1234", "$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy"));
    }

    @Test
    @Transactional
    public void testUpdate(){
        User user = new User();
        user.setId(6L);
        user.setNickName("test");
        User record = userRepository.getReferenceById(user.getId());
        BeanCopyUtils.updateBeanNonNull(user,record);
        System.out.println(record);
    }
}
