package com.springboot.farm2marketbackend.service;

import com.springboot.farm2marketbackend.data.entity.Image;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image uploadImage(MultipartFile file) throws IOException;
    byte[] downloadImage(@PathVariable("fileName") String fileName);

    void deleteSupplierBoard(Long id);
}
