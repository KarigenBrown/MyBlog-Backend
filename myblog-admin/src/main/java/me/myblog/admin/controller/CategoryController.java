package me.myblog.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Category;
import me.myblog.framework.domain.vo.CategoryVo;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.service.CategoryService;
import me.myblog.framework.utils.BeanCopyUtils;
import me.myblog.framework.utils.WebUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
// @RequestMapping("/category")
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper mapper;

    // @GetMapping("/list")
    @GetMapping("/listAllCategory")
    public Response<List<CategoryVo>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryVo> data = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return Response.ok(data);
    }

    @PreAuthorize("permissionService.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws JsonProcessingException {
        // 设置下载文件的请求头
        WebUtils.setDownLoadHeader("分类.xlsx", response);

        // 获取需要导出的数据
        List<Category> categories = categoryService.getAllCategories();

        // 把数据写入excel中
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("分类导出");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("分类名");
            headerRow.createCell(1).setCellValue("描述");
            headerRow.createCell(2).setCellValue("状态");

            for (int rowNum = 1; rowNum <= categories.size(); rowNum++) {
                Row row = sheet.createRow(rowNum);
                Category category = categories.get(rowNum - 1);
                row.createCell(0).setCellValue(category.getName());
                row.createCell(1).setCellValue(category.getDescription());
                row.createCell(2).setCellValue(category.getStatus());
            }

            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            // 如果出现异常也要响应json
            response.reset();
            Response<String> error = Response.error(ResponseStatusEnum.SYSTEM_ERROR);
            WebUtils.renderString(response,mapper.writeValueAsString(error));
        }
    }
}
