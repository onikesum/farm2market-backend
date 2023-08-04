package com.springboot.farm2marketbackend.controller;

import com.springboot.farm2marketbackend.data.dto.ImageDto;
import com.springboot.farm2marketbackend.data.dto.QuestionResponseDto;
import com.springboot.farm2marketbackend.data.dto.SupplierBoardDto;
import com.springboot.farm2marketbackend.data.entity.Image;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import com.springboot.farm2marketbackend.service.ImageService;
import com.springboot.farm2marketbackend.service.SupplierBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@RequestMapping("/supplier-board")
public class SupplierBoardController {
    final private SupplierBoardService supplierBoardService;

    @Autowired
    public SupplierBoardController(SupplierBoardService supplierBoardService) {
        this.supplierBoardService = supplierBoardService;
    }

    @PostMapping()
    public ResponseEntity<SupplierBoardDto> createSupplierBoard(
            @RequestBody SupplierBoardDto SupplierBoardDto, HttpServletRequest request) throws IOException {
        SupplierBoardDto supplierBoardDtoResponse = supplierBoardService.saveSupplierBoard(
                SupplierBoardDto);

        LOGGER.info("호출 API: " + "create SupplierBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(supplierBoardDtoResponse);
    }

    @GetMapping()
    public ResponseEntity<SupplierBoardDto> getSupplierBoard(Long id, HttpServletRequest request)
    {
        SupplierBoardDto supplierBoardDtoResponse = supplierBoardService.getSupplierBoard(id);

        LOGGER.info("호출 API: " + "get SupplierBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(supplierBoardDtoResponse);
    }

    @PutMapping()
    public ResponseEntity<SupplierBoardDto> updateSupplierBoard(
            @RequestBody  SupplierBoardDto supplierBoardDto, HttpServletRequest request) throws Exception
    {
        SupplierBoardDto supplierBoardDtoResponse = supplierBoardService.updateSupplierBoard(supplierBoardDto.getId(),
                supplierBoardDto);

        LOGGER.info("호출 API: " + "update SupplierBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(supplierBoardDtoResponse);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteSupplierBoard (Long id, HttpServletRequest request) throws Exception
    {
        supplierBoardService.deleteSupplierBoard(id);

        LOGGER.info("호출 API: " + "delete SupplierBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 삭제 되었습니다.");
    }
}