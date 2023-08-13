package com.springboot.farm2marketbackend.service;

import com.springboot.farm2marketbackend.data.dto.SupplierBoardDto;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SupplierBoardService {

    SupplierBoardDto saveSupplierBoard(SupplierBoardDto supplierBoardDto, MultipartFile imageFile) throws IOException;
    SupplierBoardDto updateSupplierBoard(Long id, SupplierBoardDto supplierBoardDto) throws Exception;
    void deleteSupplierBoard(Long id) throws Exception;
    SupplierBoardDto getSupplierBoard(Long id);
    List<SupplierBoard> getAllSupplierBoard();


}
