package com.springboot.farm2marketbackend.service.impl;

import com.springboot.farm2marketbackend.data.entity.Image;
import com.springboot.farm2marketbackend.repository.ImageRepository;
import com.springboot.farm2marketbackend.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    public Image uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            // 클라이언트가 이미지 파일을 전송하지 않은 경우, 예외 처리 로직을 추가하여 필요한 동작 수행
            throw new IllegalArgumentException("이미지 파일이 전송되지 않았습니다.");
        }
        log.info("upload file: {}", file);
        byte[] imageData = ImageUtils.compressImage(file.getBytes());

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .imgType(file.getContentType())
                .imageData(imageData)
                .build();

        image = imageRepository.save(image);

        if (image != null) {
            log.info("imageData: {}", image);
            return image;
        }

        return null;
    }



    // 이미지 파일로 압축하기
    public byte[] downloadImage(Long fileId) {

            Image image = imageRepository.findById(fileId)
                    .orElseThrow(() -> new RuntimeException("Image not found with name: " + fileId));
        log.info("download imageData: {}", image);

        return ImageUtils.decompressImage(image.getImageData());
    }

    @Override
    public void deleteSupplierBoard(Long id) {
        imageRepository.deleteById(id);
    }
}