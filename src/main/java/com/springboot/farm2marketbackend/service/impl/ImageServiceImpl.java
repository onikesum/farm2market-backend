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
        log.info("upload file: {}", file);

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .imgType(file.getContentType())
                .build();

        // 이미지 데이터를 바이트 배열로 변환하여 설정합니다.
        image.setImageData(file.getBytes());

        // 데이터베이스에 이미지 정보를 저장합니다.
        return imageRepository.save(image);
    }



    // 이미지 파일로 압축하기
    public byte[] downloadImage(String fileName) {

            Image image = imageRepository.findByName(fileName)
                    .orElseThrow(() -> new RuntimeException("Image not found with name: " + fileName));

        log.info("download imageData: {}", image);

        return ImageUtils.decompressImage(image.getImageData());
    }
}