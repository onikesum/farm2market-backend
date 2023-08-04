package com.springboot.farm2marketbackend.service.impl;

import com.springboot.farm2marketbackend.data.dao.SupplierBoardDAO;
import com.springboot.farm2marketbackend.data.dto.SupplierBoardDto;
import com.springboot.farm2marketbackend.data.entity.*;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import com.springboot.farm2marketbackend.repository.SupplierBoardRepository;
import com.springboot.farm2marketbackend.service.SupplierBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SupplierBoardServiceImpl implements SupplierBoardService {
    private final SupplierBoardDAO supplierBoardDAO;

    @Autowired
    public SupplierBoardServiceImpl(SupplierBoardDAO supplierBoardDAO) {
        this.supplierBoardDAO = supplierBoardDAO;
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
        supplierBoardDto.setCreatedDate(LocalDateTime.now());
        supplierBoardDto.setModifiedDate(LocalDateTime.now());

        return supplierBoardDto;
    }

    @Override
    @Transactional
    public SupplierBoardDto saveSupplierBoard(SupplierBoardDto supplierBoardDto) throws IOException {
        SupplierBoard supplierBoard = new SupplierBoard();

        supplierBoard.setId(supplierBoardDto.getId());
        supplierBoard.setName(supplierBoardDto.getName());
        supplierBoard.setPrice(supplierBoardDto.getPrice());
        supplierBoard.setProduct(supplierBoardDto.getProduct());
        supplierBoard.setKeyword(supplierBoardDto.getKeyword());
        supplierBoard.setSupplier_id(supplierBoardDto.getSupplier_id());
        supplierBoard.setCreatedDate(LocalDateTime.now());
        supplierBoard.setModifiedDate(LocalDateTime.now());

        SupplierBoard savedsupplierBoard = supplierBoardDAO.insertSupplierBoard(supplierBoard);

        SupplierBoardDto SupplierBoardDto  = new SupplierBoardDto();

        SupplierBoardDto.setId(supplierBoardDto.getId());
        SupplierBoardDto.setName(supplierBoardDto.getName());
        SupplierBoardDto.setPrice(supplierBoardDto.getPrice());
        SupplierBoardDto.setProduct(supplierBoardDto.getProduct());
        SupplierBoardDto.setKeyword(supplierBoardDto.getKeyword());
        SupplierBoardDto.setSupplier_id(supplierBoardDto.getSupplier_id());
        SupplierBoardDto.setCreatedDate(LocalDateTime.now());
        SupplierBoardDto.setModifiedDate(LocalDateTime.now());
        return SupplierBoardDto;
    }

    @Override
    public SupplierBoardDto updateSupplierBoard(Long id, SupplierBoardDto supplierBoardDto) throws Exception {
        SupplierBoard supplierBoard = supplierBoardDAO.selectSupplierBoard(id);

        if (supplierBoard == null) {
            throw new Exception("해당 id에 해당하는 데이터가 존재하지 않습니다.");
        }
        supplierBoard.setId(supplierBoardDto.getId());
        supplierBoard.setName(supplierBoardDto.getName());
        supplierBoard.setPrice(supplierBoardDto.getPrice());
        supplierBoard.setProduct(supplierBoardDto.getProduct());
        supplierBoard.setKeyword(supplierBoardDto.getKeyword());
        supplierBoard.setSupplier_id(supplierBoardDto.getSupplier_id());
        supplierBoard.setModifiedDate(LocalDateTime.now());

        SupplierBoard updatedSupplierBoard = supplierBoardDAO.updateSupplierBoard(supplierBoard);
        SupplierBoardDto updatedSupplierDto =new SupplierBoardDto();

        updatedSupplierDto.setId(supplierBoardDto.getId());
        updatedSupplierDto.setName(supplierBoardDto.getName());
        updatedSupplierDto.setPrice(supplierBoardDto.getPrice());
        updatedSupplierDto.setProduct(supplierBoardDto.getProduct());
        updatedSupplierDto.setKeyword(supplierBoardDto.getKeyword());
        updatedSupplierDto.setSupplier_id(supplierBoardDto.getSupplier_id());
        updatedSupplierDto.setModifiedDate(LocalDateTime.now());
        return updatedSupplierDto;
    }


    @Override
    @Transactional
    public void deleteSupplierBoard(Long id) throws Exception {
        supplierBoardDAO.deleteSupplierBoard(id);
    }

}
