package me.myblog.framework.utils;

import lombok.SneakyThrows;
import me.myblog.framework.config.OssConfig;
import me.myblog.framework.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Component
public class OssUtils {
    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private S3Client ossClient;

    private String composeUrl(String eTag) {
        return ossConfig.getEndpoint() + "/" + eTag;
    }

    @SneakyThrows(Exception.class)
    public String uploadObject(String fileName, MultipartFile file) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(ossConfig.getBucketName())
                .contentDisposition(file.getContentType())
                .build();

        // 私有bucket
        /*PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .putObjectRequest(request)
                .signatureDuration(Duration.ofDays(SystemConstants.DURATION))
                .build();

        ossClient.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return ossConfig.getPresigner().presignPutObject(presignRequest).url().toString();*/

        // 公有bucket
        String eTag = ossClient.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize())).eTag();
        return this.composeUrl(eTag);
    }

    public String getObjectUrl(String fileName) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(ossConfig.getBucketName())
                .key(fileName)
                .build();

        // 私有bucket
        /*GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(request)
                .signatureDuration(Duration.ofDays(SystemConstants.DURATION))
                .build();

        return ossConfig.getPresigner().presignGetObject(presignRequest).url().toString();*/

        // 公有bucket
        String eTag = ossClient.getObject(request).response().eTag();
        return this.composeUrl(eTag);
    }
}
