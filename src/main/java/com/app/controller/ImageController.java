package com.app.controller;

import com.app.dto.ImageDto;
import com.app.model.Image;
import com.app.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RedisHash
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService service;

    @PostMapping("/upload")
    public ImageDto upload(MultipartFile file) throws Exception {
        Image image = service.upload(file);
        return toDto(image);
    }

    @GetMapping(value = "/download/{uid}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] download(@PathVariable String uid) throws Exception {
        return service.download(uid);
    }

    @Cacheable(value = "ImageController::getInfo", key = "#uid")
    @GetMapping("/info/{uid}")
    public ImageDto getInfo(@PathVariable String uid) {
        Image image = service.getInfo(uid);
        return toDto(image);
    }

    private ImageDto toDto(Image image) {
        return new ImageDto(
            image.getName(),
            image.getSize(),
            image.getUid()
        );
    }
}
