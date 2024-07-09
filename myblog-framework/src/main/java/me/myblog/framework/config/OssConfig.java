package me.myblog.framework.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Getter
@Configuration
@ConfigurationProperties(prefix = "oss")
public class OssConfig {
    private String endpoint;
    private String bucketName;
    private String accessKey;
    private String secretKey;

    private AwsBasicCredentials awsCredentials;
    private StaticCredentialsProvider credentialsProvider;
    private S3Presigner presigner;

    @Bean
    protected S3Client s3Client() {
        this.awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        this.credentialsProvider = StaticCredentialsProvider.create(awsCredentials);
        this.presigner = S3Presigner.builder()
                .credentialsProvider(this.credentialsProvider)
                .build();
        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
