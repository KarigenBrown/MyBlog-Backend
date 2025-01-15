package me.myblog.framework.service;

import jakarta.persistence.criteria.*;
import me.myblog.framework.constants.SystemConstants;
import me.myblog.framework.domain.entity.Article;
import me.myblog.framework.domain.entity.Category;
import me.myblog.framework.domain.meta.Category_;
import me.myblog.framework.repository.ArticleRepository;
import me.myblog.framework.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Lazy
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RestClient.Builder builder;

    public List<Category> getAllCategory() {
        Article article = new Article();
        // 查询文章表，状态为已发布的文章
        article.setStatus(SystemConstants.ARTICLE_STATUS_NORMAL);
        // 获取文章的分类id，并且去重
        Set<Long> categoryIds = articleRepository.findAll(Example.of(article))
                .stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());

        return categoryRepository.findAll((Specification<Category>) (root, query, criteriaBuilder) -> {
            Path<Object> id = root.get(Category_.ID);

            return criteriaBuilder.in(id).value(categoryIds);
        });
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.getReferenceById(categoryId);
    }

    public List<Category> getAllCategories() {
        Category example = new Category();
        example.setStatus(SystemConstants.ARTICLE_STATUS_NORMAL);
        return categoryRepository.findAll(Example.of(example));
    }

    public List<Category> getCategoryList(Integer pageNum, Integer pageSize, String name, String status) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Category category = new Category();
        category.setName(name);
        category.setStatus(status.charAt(0));
        return categoryRepository.findAll((root, query, builder) -> {
            Predicate n = builder.like(root.get(Category_.NAME), name);
            Predicate s = builder.equal(root.get(Category_.STATUS), status);
            query.where(builder.and(n, s));
            return query.getRestriction();
        }, pageRequest).getContent();
    }

    public void save(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
