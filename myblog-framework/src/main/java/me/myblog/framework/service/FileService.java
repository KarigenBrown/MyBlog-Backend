package me.myblog.framework.service;

import me.myblog.framework.enums.ResponseStatusEnum;
import me.myblog.framework.exception.SystemException;
import me.myblog.framework.utils.OssUtils;
import me.myblog.framework.utils.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    @Autowired
    private OssUtils ossUtils;

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
}
