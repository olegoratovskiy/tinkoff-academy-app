package com.app.service;

import com.app.model.Image;
import com.app.model.Operation;
import com.app.repository.ImageRepository;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository repository;

    private final OperationService operationService;

    private final StorageService storageService;

    public Image checkAndGet(String uid) {
        Optional<Image> image = repository.findByUid(uid);
        if (image.isEmpty()) {
            throw new NoSuchElementException("Image with uid " + uid + " not found");
        }
        return image.get();
    }

    public Image getInfo(String uid) {
        Image image = checkAndGet(uid);

        operationService.logOperation(
            String.format("Read image info: %s", image),
            LocalDateTime.now(),
            Operation.OperationType.READ
        );

        return image;
    }

    public byte[] download(String uid) throws Exception {
        checkAndGet(uid);

        return storageService.downloadImage(uid);
    }

    public Image upload(MultipartFile file) throws Exception {
        String uid = storageService.uploadImage(file);

        Image image = repository.save(Image.builder()
            .size(file.getSize())
            .name(file.getOriginalFilename())
            .uid(uid)
            .build()
        );

        operationService.logOperation(
            String.format("Upload image: %s", image),
            LocalDateTime.now(),
            Operation.OperationType.WRITE
        );

        return image;
    }
}
