package me.myblog.framework.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import me.myblog.framework.domain.Response;
import me.myblog.framework.domain.entity.Category;
import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.utils.OssUtils;
import me.myblog.framework.utils.PathUtils;
import me.myblog.framework.utils.WebUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private OssUtils ossUtils;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper mapper;

    public String uploadPicture(MultipartFile picture) {
        // 判断文件类型
        // 获取原始文件名
        String originalFilename = picture.getOriginalFilename();
        // 对原始文件名进行判断
        if (originalFilename == null || !originalFilename.matches("^.+\\.(png|jpg|jpeg)$")) {
            throw new SystemException(ResponseStatusEnum.FILE_TYPE_ERROR);
        }
        // 4.
        String fileName = PathUtils.dateUuidPath(originalFilename);
        // 如果判断通过上传文件到OSS
        return ossUtils.uploadPublicObject(fileName, picture);
    }

    @SneakyThrows
    public void exportExcel(HttpServletResponse response) {
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
            WebUtils.renderString(response, mapper.writeValueAsString(error));
        }
    }
}
