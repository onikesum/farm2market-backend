package com.springboot.farm2marketbackend.service.impl;

import com.springboot.farm2marketbackend.data.dao.SellerBoardDAO;
import com.springboot.farm2marketbackend.data.dto.ImageDto;
import com.springboot.farm2marketbackend.data.dto.SellerBoardDto;
import com.springboot.farm2marketbackend.data.entity.Image;
import com.springboot.farm2marketbackend.data.entity.SellerBoard;
import com.springboot.farm2marketbackend.service.ImageService;
import com.springboot.farm2marketbackend.service.SellerBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
@Slf4j
@Service
public class SellerBoardServiceImpl implements SellerBoardService {
    private final SellerBoardDAO sellerBoardDAO;
    private final ImageService imageService;
    @Autowired
    public SellerBoardServiceImpl(SellerBoardDAO sellerBoardDAO, ImageService imageService) {
        this.sellerBoardDAO = sellerBoardDAO;
        this.imageService = imageService;
    }

    @Override
    public SellerBoardDto getSellerBoard(Long id) {

        SellerBoard sellerBoard = sellerBoardDAO.selectSellerBoard(id);
        SellerBoardDto sellerBoardDto = new SellerBoardDto();

        sellerBoardDto.setId(sellerBoard.getId());
        sellerBoardDto.setName(sellerBoard.getName());
        sellerBoardDto.setPrice(sellerBoard.getPrice());
        sellerBoardDto.setProduct(sellerBoard.getProduct());
        sellerBoardDto.setKeyword(sellerBoard.getKeyword());
        sellerBoardDto.setCreatedDate(sellerBoard.getCreatedDate());
        sellerBoardDto.setModifiedDate(sellerBoard.getModifiedDate());

        // 이미지 정보를 가져올 수 있도록 추가
        if (sellerBoard.getImage() != null) {
            Image image = sellerBoard.getImage();
            sellerBoardDto.setImageId(image.getId());             // 이미지 ID 설정
            sellerBoardDto.setImageName(image.getName());         // 이미지 파일명 설정
            sellerBoardDto.setImageType(image.getImgType());      // 이미지 파일 타입 설정
            sellerBoardDto.setImageData(image.getImageData());    // 이미지 데이터 설정
        }

        return sellerBoardDto;
    }

    @Override
    @Transactional
    public SellerBoardDto saveSellerBoard(SellerBoardDto sellerBoardDto, MultipartFile imageFile) throws IOException {
        // SellerBoardDto에서 SellerBoard로 변환
        SellerBoard sellerBoard = sellerBoardDto.toEntity();
// 이미지 데이터를 byte[]로 변환하여 SellerBoard에 설정
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageData = imageFile.getBytes();
            sellerBoard.setImageData(imageData);
        }
        // 이미지 데이터를 Image 객체로 생성하여 SellerBoard에 설정
        if (sellerBoardDto.getImageData() != null) {
            ImageDto imageDto = ImageDto.builder()
                    .name(sellerBoardDto.getImageName())
                    .imgType(sellerBoardDto.getImageType())
                    .imageData(sellerBoardDto.getImageData())
                    .seller_id(sellerBoard.getId())
                    .build();
            Image image = imageService.uploadImage((MultipartFile) imageDto);
            sellerBoard.setImage(image);
            sellerBoard.setImageId(image.getId());
            sellerBoardDto.setImageId(image.getId());
        }
        SellerBoard savedSellerBoard = sellerBoardDAO.insertSellerBoard(sellerBoard);

        // SellerBoard를 SellerBoardDto로 변환하여 리턴
        return sellerBoardDto.builder()
                .id(savedSellerBoard.getId())
                .name(savedSellerBoard.getName())
                .product(savedSellerBoard.getProduct())
                .price(savedSellerBoard.getPrice())
                .keyword(savedSellerBoard.getKeyword())
                .createdDate(savedSellerBoard.getCreatedDate())
                .modifiedDate(savedSellerBoard.getModifiedDate())
                .imageId(sellerBoard.getId())
                .build();
    }

    @Override
    @Transactional
    public SellerBoardDto updateSellerBoard(Long id, SellerBoardDto sellerBoardDto) throws Exception {
        SellerBoard sellerBoard = sellerBoardDAO.selectSellerBoard(id);

        if (sellerBoard == null) {
            throw new Exception("해당 id에 해당하는 데이터가 존재하지 않습니다.");
        }

        // 필요한 필드만 업데이트
        if (sellerBoardDto.getName() != null) {
            sellerBoard.setName(sellerBoardDto.getName());
        }
        if (sellerBoardDto.getPrice() != null) {
            sellerBoard.setPrice(sellerBoardDto.getPrice());
        }
        if (sellerBoardDto.getProduct() != null) {
            sellerBoard.setProduct(sellerBoardDto.getProduct());
        }
        if (sellerBoardDto.getKeyword() != null) {
            sellerBoard.setKeyword(sellerBoardDto.getKeyword());
        }
        if (sellerBoardDto.getImageData() != null) {
            // 이미지 데이터를 Image 객체로 생성하여 SellerBoard에 설정
            ImageDto imageDto = ImageDto.builder()
                    .name(sellerBoardDto.getImageName())
                    .imgType(sellerBoardDto.getImageType())
                    .imageData(sellerBoardDto.getImageData())
                    .seller_id(sellerBoard.getId())
                    .build();
            Image image = imageService.uploadImage((MultipartFile) imageDto);
            sellerBoard.setImage(image);
            sellerBoard.setImageId(image.getId());
        }

        sellerBoard.setModifiedDate(LocalDateTime.now());

        SellerBoard updatedSellerBoard = sellerBoardDAO.updateSellerBoard(sellerBoard);

        // SellerBoard를 SellerBoardDto로 변환하여 리턴
        return sellerBoardDto.builder()
                .id(updatedSellerBoard.getId())
                .name(updatedSellerBoard.getName())
                .product(updatedSellerBoard.getProduct())
                .price(updatedSellerBoard.getPrice())
                .keyword(updatedSellerBoard.getKeyword())
                .createdDate(updatedSellerBoard.getCreatedDate())
                .modifiedDate(updatedSellerBoard.getModifiedDate())
                .imageId(updatedSellerBoard.getImageId())
                .build();
    }


    @Override
    @Transactional
    public void deleteSellerBoard(Long id) throws Exception {
        sellerBoardDAO.deleteSellerBoard(id);
    }

}
