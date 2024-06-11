package com.app.service;

import com.app.config.MinioProperties;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final MinioClient client;
    private final MinioProperties properties;

    public String uploadImage(MultipartFile file) throws Exception {
        String uid = UUID.randomUUID().toString();

        InputStream inputStream = new ByteArrayInputStream(file.getBytes());
        client.putObject(
            PutObjectArgs.builder()
                .bucket(properties.getBucket())
                .object(uid)
                .stream(inputStream, file.getSize(), properties.getImageSize())
                .contentType(file.getContentType())
                .build()
        );

        return uid;
    }

    public byte[] downloadImage(String uid) throws Exception {
        return IOUtils.toByteArray(client.getObject(
            GetObjectArgs.builder()
                .bucket(properties.getBucket())
                .object(uid)
                .build()));
    }
}
