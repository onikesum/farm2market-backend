package com.springboot.farm2marketbackend.service.impl;

import com.springboot.farm2marketbackend.data.dao.SupplierBoardDAO;
import com.springboot.farm2marketbackend.data.dto.ImageDto;
import com.springboot.farm2marketbackend.data.dto.SupplierBoardDto;
import com.springboot.farm2marketbackend.data.entity.*;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import com.springboot.farm2marketbackend.service.ImageService;
import com.springboot.farm2marketbackend.service.SupplierBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

@Slf4j
@Service
public class SupplierBoardServiceImpl implements SupplierBoardService {
    private final SupplierBoardDAO supplierBoardDAO;
    private final ImageService imageService;
    @Autowired
    public SupplierBoardServiceImpl(SupplierBoardDAO supplierBoardDAO, ImageService imageService) {
        this.supplierBoardDAO = supplierBoardDAO;
        this.imageService = imageService;
    }

    @Override
    public SupplierBoardDto getSupplierBoard(Long id) {

        SupplierBoard supplierBoard = supplierBoardDAO.selectSupplierBoard(id);
        SupplierBoardDto supplierBoardDto = new SupplierBoardDto();

        supplierBoardDto.setId(supplierBoard.getId());
        supplierBoardDto.setName(supplierBoard.getName());
        supplierBoardDto.setPrice(supplierBoard.getPrice());
        supplierBoardDto.setProduct(supplierBoard.getProduct());
        supplierBoardDto.setKeyword(supplierBoard.getKeyword());
        supplierBoardDto.setSupplier_id(supplierBoard.getSupplier_id());
        supplierBoardDto.setCreatedDate(supplierBoard.getCreatedDate());
        supplierBoardDto.setModifiedDate(supplierBoard.getModifiedDate());

        // 이미지 정보를 가져올 수 있도록 추가
        if (supplierBoard.getImage() != null) {
            Image image = supplierBoard.getImage();
            supplierBoardDto.setImageId(image.getId());             // 이미지 ID 설정
            supplierBoardDto.setImageName(image.getName());         // 이미지 파일명 설정
            supplierBoardDto.setImageType(image.getImgType());      // 이미지 파일 타입 설정
            supplierBoardDto.setImageData(image.getImageData());    // 이미지 데이터 설정
        }

        return supplierBoardDto;
    }

    @Override
    @Transactional
    public SupplierBoardDto saveSupplierBoard(SupplierBoardDto supplierBoardDto, MultipartFile imageFile) throws IOException {
        // SupplierBoardDto에서 SupplierBoard로 변환
        SupplierBoard supplierBoard = supplierBoardDto.toEntity();
// 이미지 데이터를 byte[]로 변환하여 SupplierBoard에 설정
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageData = imageFile.getBytes();
            supplierBoard.setImageData(imageData);
        }
        // 이미지 데이터를 Image 객체로 생성하여 SupplierBoard에 설정
        if (supplierBoardDto.getImageData() != null) {
            ImageDto imageDto = ImageDto.builder()
                    .name(supplierBoardDto.getImageName())
                    .imgType(supplierBoardDto.getImageType())
                    .imageData(supplierBoardDto.getImageData())
                    .build();
            Image image = imageService.uploadImage((MultipartFile) imageDto);
            supplierBoard.setImage(image);
            supplierBoard.setImageId(image.getId());
            supplierBoardDto.setImageId(image.getId());

        }
//        if (supplierBoardDto.getImageData() != null) {
//            supplierBoard.setImageData(supplierBoardDto.getImageData());
//        }


        SupplierBoard savedSupplierBoard = supplierBoardDAO.insertSupplierBoard(supplierBoard);

        // SupplierBoard를 SupplierBoardDto로 변환하여 리턴
        return SupplierBoardDto.builder()
                .id(savedSupplierBoard.getId())
                .name(savedSupplierBoard.getName())
                .product(savedSupplierBoard.getProduct())
                .price(savedSupplierBoard.getPrice())
                .keyword(savedSupplierBoard.getKeyword())
                .supplier_id(savedSupplierBoard.getSupplier_id())
                .createdDate(savedSupplierBoard.getCreatedDate())
                .modifiedDate(savedSupplierBoard.getModifiedDate())
                .imageId(savedSupplierBoard.getId())
                .build();
    }

    @Override
    @Transactional
    public SupplierBoardDto updateSupplierBoard(Long id, SupplierBoardDto supplierBoardDto) throws Exception {
        SupplierBoard supplierBoard = supplierBoardDAO.selectSupplierBoard(id);

        if (supplierBoard == null) {
            throw new Exception("해당 id에 해당하는 데이터가 존재하지 않습니다.");
        }

        // 필요한 필드만 업데이트
        if (supplierBoardDto.getName() != null) {
            supplierBoard.setName(supplierBoardDto.getName());
        }
        if (supplierBoardDto.getPrice() != null) {
            supplierBoard.setPrice(supplierBoardDto.getPrice());
        }
        if (supplierBoardDto.getProduct() != null) {
            supplierBoard.setProduct(supplierBoardDto.getProduct());
        }
        if (supplierBoardDto.getKeyword() != null) {
            supplierBoard.setKeyword(supplierBoardDto.getKeyword());
        }
        if (supplierBoardDto.getImageData() != null) {
            // 이미지 데이터를 Image 객체로 생성하여 SupplierBoard에 설정
            ImageDto imageDto = ImageDto.builder()
                    .name(supplierBoardDto.getImageName())
                    .imgType(supplierBoardDto.getImageType())
                    .imageData(supplierBoardDto.getImageData())
                    .build();
            Image image = imageService.uploadImage((MultipartFile) imageDto);
            supplierBoard.setImage(image);
            supplierBoard.setImageId(image.getId());
        }

        supplierBoard.setModifiedDate(LocalDateTime.now());

        SupplierBoard updatedSupplierBoard = supplierBoardDAO.updateSupplierBoard(supplierBoard);

        // SupplierBoard를 SupplierBoardDto로 변환하여 리턴
        return SupplierBoardDto.builder()
                .id(updatedSupplierBoard.getId())
                .name(updatedSupplierBoard.getName())
                .product(updatedSupplierBoard.getProduct())
                .price(updatedSupplierBoard.getPrice())
                .keyword(updatedSupplierBoard.getKeyword())
                .supplier_id(updatedSupplierBoard.getSupplier_id())
                .createdDate(updatedSupplierBoard.getCreatedDate())
                .modifiedDate(updatedSupplierBoard.getModifiedDate())
                .imageId(updatedSupplierBoard.getId())
                .build();
    }


    @Override
    @Transactional
    public void deleteSupplierBoard(Long id) throws Exception {
        supplierBoardDAO.deleteSupplierBoard(id);
    }

}
