package com.springboot.farm2marketbackend.controller;

import com.springboot.farm2marketbackend.data.dto.ImageDto;
import com.springboot.farm2marketbackend.data.dto.SupplierBoardDto;
import com.springboot.farm2marketbackend.data.entity.Image;
import com.springboot.farm2marketbackend.data.entity.SupplierBoard;
import com.springboot.farm2marketbackend.data.entity.SupplierBoardAndImageResponse;
import com.springboot.farm2marketbackend.service.ImageService;
import com.springboot.farm2marketbackend.service.SupplierBoardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@RequestMapping("/supplier-board")
public class SupplierBoardController {
    final private SupplierBoardService supplierBoardService;
    final private ImageService imageService;

    @Autowired
    public SupplierBoardController(SupplierBoardService supplierBoardService, ImageService imageService) {
        this.supplierBoardService = supplierBoardService;
        this.imageService = imageService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping
    public ResponseEntity<SupplierBoardDto> createSupplierBoard(
            @RequestParam("file") MultipartFile imageFile,
            @RequestParam("name") String name,
            @RequestParam("product") String product,
            @RequestParam("price") Long price,
            @RequestParam("keyword") String keyword,
            @RequestParam("supplier_id") Long supplier_id
    ) throws IOException {
        Image image = imageService.uploadImage(imageFile);
        // SupplierBoardDto로부터 SupplierBoard 객체 생성
        SupplierBoardDto supplierBoardDto = SupplierBoardDto.builder()
                .name(name)
                .product(product)
                .price(price)
                .keyword(keyword)
                .supplier_id(supplier_id)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .imageId(image.getId())
                .build();

        // SupplierBoard와 Image를 저장
        supplierBoardDto.setImage(image);
        SupplierBoardDto savedSupplierBoardDto = supplierBoardService.saveSupplierBoard(supplierBoardDto, imageFile);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplierBoardDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SupplierBoardDto> getSupplierBoard(@PathVariable Long id, HttpServletRequest request) {
        SupplierBoardDto supplierBoardDtoResponse = supplierBoardService.getSupplierBoard(id);

        // 이미지 정보도 함께 제공하도록 수정
        LOGGER.info("호출 API: " + "get SupplierBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " + LocalDateTime.now());
        if (supplierBoardDtoResponse.getImageData() != null) {
            // 이미지 데이터를 Base64 인코딩하여 문자열로 변환하여 전달
            String base64ImageData = Base64.getEncoder().encodeToString(supplierBoardDtoResponse.getImageData());
            supplierBoardDtoResponse.setImageData(base64ImageData.getBytes());
        }

        return ResponseEntity.status(HttpStatus.OK).body(supplierBoardDtoResponse);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SupplierBoardDto> updateSupplierBoard(
            @PathVariable Long id,
            @RequestPart(required = false) MultipartFile imageFile,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) String keyword
    ) throws Exception {
        SupplierBoardDto supplierBoardDto = SupplierBoardDto.builder()
                .id(id)
                .name(name)
                .product(product)
                .price(price)
                .keyword(keyword)
                .build();
        if (imageFile != null) {
            Image image = imageService.uploadImage(imageFile);
            supplierBoardDto.setImage(image);
        }
        SupplierBoardDto supplierBoardDtoResponse = supplierBoardService.updateSupplierBoard(id, supplierBoardDto);
        return ResponseEntity.status(HttpStatus.OK).body(supplierBoardDtoResponse);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping()
    public ResponseEntity<String> deleteSupplierBoard (Long id, HttpServletRequest request) throws Exception
    {
        supplierBoardService.deleteSupplierBoard(id);

        LOGGER.info("호출 API: " + "delete SupplierBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 삭제 되었습니다.");
    }
}