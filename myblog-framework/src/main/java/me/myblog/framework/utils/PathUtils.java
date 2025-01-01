package me.myblog.framework.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PathUtils {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static String dateUuidPath(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(index);
        return formatter.format(LocalDate.now()) + "/" + UUID.randomUUID() + fileType;
    }
}