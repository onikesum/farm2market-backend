package com.springboot.farm2marketbackend.service;

import com.springboot.farm2marketbackend.data.dto.SellerBoardDto;
import com.springboot.farm2marketbackend.data.entity.SellerBoard;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SellerBoardService {
    SellerBoardDto saveSellerBoard(SellerBoardDto sellerBoardDto, MultipartFile imageFile) throws IOException;
    SellerBoardDto updateSellerBoard(Long id, SellerBoardDto sellerBoardDto) throws Exception;
    void deleteSellerBoard(Long id) throws Exception;
    SellerBoardDto getSellerBoard(Long id);

    List<SellerBoard> getAllSellerBoard();
}
