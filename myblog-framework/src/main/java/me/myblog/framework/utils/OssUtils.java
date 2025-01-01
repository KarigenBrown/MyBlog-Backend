package me.myblog.framework.utils;

import lombok.SneakyThrows;
import me.myblog.framework.config.OssConfig;
import me.myblog.framework.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Component
public class OssUtils {
    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private S3Client ossClient;

    @SneakyThrows
    public String uploadPublicObject(String fileName, MultipartFile file) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(ossConfig.getBucketName())
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        // 公有bucket
        ossClient.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize())).eTag();
        return ossClient.utilities()
                .getUrl(r->r.bucket(ossConfig.getBucketName()).key(fileName))
                .toExternalForm();
    }

    public String getPublicObjectUrl(String fileName) {
        return ossClient.utilities()
                .getUrl(r->r.bucket(ossConfig.getBucketName()).key(fileName))
                .toExternalForm();
    }
}
