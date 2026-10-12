package com.example.petlog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

import java.net.URI;

@Configuration
public class RustFSConfig {

    @Value("${cloud.aws.s3.access-key}")
    private String accessKey;
    @Value("${cloud.aws.s3.secret-key}")
    private String secretKey;
    @Value("${cloud.aws.region}")
    private String region;
    // rustFS
    @Value("${cloud.aws.s3.endpoint:}")
    private String endpoint;
    // rustFS
    @Value("${cloud.aws.s3.path-style-access-enabled:false}")
    private boolean pathStyleAccessEnabled;

    @Bean
    public S3Client rustFSClient() {
        AwsBasicCredentials awsBasicCredentials =
                AwsBasicCredentials.create(accessKey, secretKey);
        S3ClientBuilder builder = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .region(Region.of(region))
                .forcePathStyle(pathStyleAccessEnabled);

        if (endpoint != null && !endpoint.isBlank()) {
            builder.endpointOverride(URI.create(endpoint));
        }

        return builder.build();

    }

}
