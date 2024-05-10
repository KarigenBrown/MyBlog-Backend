package me.myblog.framework.utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletResponse;
import me.myblog.framework.constants.SystemConstants;
import org.apache.http.entity.ContentType;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebUtils {

    private WebUtils() {
    }

    public static void renderString(HttpServletResponse response, String string) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDownLoadHeader(String filename, ServletContext context, HttpServletResponse response) {
        String mimeType = context.getMimeType(filename);
        response.setContentType(mimeType);
        String urlFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        response.setHeader(SystemConstants.ContentDisposition, ContentDisposition.attachment().filename(urlFilename).build().toString());
    }

    public static void setDownLoadHeader(String filename, HttpServletResponse response) {
        response.setContentType(ContentType.parse("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet").toString());
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        String urlFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        response.setHeader(SystemConstants.ContentDisposition, ContentDisposition.attachment().filename(urlFilename).build().toString());
    }

    public static Integer calculatePageOffset(Integer pageNumber, Integer pageSize) {
        return (pageNumber - 1) * pageSize;
    }
}
