package me.myblog.admin.controller;

import jakarta.servlet.http.HttpServletResponse;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Category;
import me.myblog.framework.domain.vo.CategoryVo;
import me.myblog.framework.domain.vo.PageVo;
import me.myblog.framework.service.CategoryService;
import me.myblog.framework.service.FileService;
import me.myblog.framework.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequestMapping("/category")
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileService fileService;

    // @GetMapping("/list")
    @GetMapping("/listAllCategory")
    public Response<List<CategoryVo>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryVo> data = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return Response.ok(data);
    }

    @PreAuthorize("permissionService.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) {
        fileService.exportExcel(response);
    }

    @GetMapping("/list")
    public Response<PageVo> getCategoryList(
            // @RequestParam("pageNumber") Integer pageNumber,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("name") String name,
            @RequestParam("status") String status
    ) {
        List<Category> categories = categoryService.getCategoryList(pageNum, pageSize, name, status);
        PageVo pageVo = new PageVo(categories, Integer.toUnsignedLong(categories.size()));
        return Response.ok(pageVo);
    }

    @PostMapping
    public Response<Object> postCategory(@RequestBody Category category) {
        categoryService.save(category);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response<Category> getById(@PathVariable("id") Long id) {
        Category category = categoryService.getCategoryById(id);
        return Response.ok(category);
    }

    @PutMapping
    public Response<Object> putCategory(@RequestBody Category category) {
        categoryService.save(category);
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteById(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return Response.ok();
    }
}
