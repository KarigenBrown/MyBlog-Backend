package me.myblog.guest.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.jooq.tables.pojos.Category;
import me.myblog.framework.domain.vo.CategoryVo;
import me.myblog.framework.service.CategoryService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // @GetMapping("/all")
    @GetMapping("/getCategoryList")
    public Response<List<CategoryVo>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return Response.ok(categoryVos);
    }
}

