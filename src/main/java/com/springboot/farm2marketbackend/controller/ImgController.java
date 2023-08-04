package com.springboot.farm2marketbackend.controller;

import com.springboot.farm2marketbackend.data.entity.Image;
import com.springboot.farm2marketbackend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImgController {

    final private ImageService storageService;

    // 업로드
    @PostMapping
    @PermitAll
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException, IOException {
        Image uploadImage = storageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    // 다운로드
    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        byte[] downloadImage = storageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

}