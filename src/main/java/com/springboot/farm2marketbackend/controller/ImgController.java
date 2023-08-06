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
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

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
    @DeleteMapping()
    public ResponseEntity<String> deleteSupplierBoard (Long id, HttpServletRequest request) throws Exception
    {
        storageService.deleteSupplierBoard(id);

        LOGGER.info("호출 API: " + "delete SupplierBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 삭제 되었습니다.");
    }

}