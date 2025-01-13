package me.myblog.admin.controller;

import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Category;
import me.myblog.framework.domain.vo.CategoryVo;
import me.myblog.framework.service.CategoryService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// @RequestMapping("/category")
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // @GetMapping("/list")
    @GetMapping("/listAllCategory")
    public Response<List<CategoryVo>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryVo> data = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return Response.ok(data);
    }
}
