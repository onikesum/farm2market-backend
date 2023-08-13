package com.springboot.farm2marketbackend.controller;

import com.springboot.farm2marketbackend.data.dto.SellerBoardDto;
import com.springboot.farm2marketbackend.data.entity.Image;
import com.springboot.farm2marketbackend.data.entity.SellerBoard;
import com.springboot.farm2marketbackend.service.ImageService;
import com.springboot.farm2marketbackend.service.SellerBoardService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@RequestMapping("/seller-board")
public class SellerBoardController {
    final private SellerBoardService sellerBoardService;
    final private ImageService imageService;

    @Autowired
    public SellerBoardController(SellerBoardService sellerBoardService, ImageService imageService) {
        this.sellerBoardService = sellerBoardService;
        this.imageService = imageService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping
    public ResponseEntity<SellerBoardDto> createSellerBoard(
            @RequestParam("file") MultipartFile imageFile,
            @RequestParam("name") String name,
            @RequestParam("product") String product,
            @RequestParam("price") Long price,
            @RequestParam("keyword") String keyword
    ) throws IOException {
        Image image = imageService.uploadImage(imageFile);
        // SellerBoardDto로부터 SellerBoard 객체 생성
        SellerBoardDto sellerBoardDto = SellerBoardDto.builder()
                .name(name)
                .product(product)
                .price(price)
                .keyword(keyword)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .image(image)
                .imageId(image.getId())
                .build();

        // SellerBoard와 Image를 저장
       // sellerBoardDto.setImage(image);
        SellerBoardDto savedSellerBoardDto = sellerBoardService.saveSellerBoard(sellerBoardDto, imageFile);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSellerBoardDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SellerBoardDto> getSellerBoard(@PathVariable Long id, HttpServletRequest request) {
        SellerBoardDto sellerBoardDtoResponse = sellerBoardService.getSellerBoard(id);

        // 이미지 정보도 함께 제공하도록 수정
        LOGGER.info("호출 API: " + "get SellerBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " + LocalDateTime.now());
        if (sellerBoardDtoResponse.getImageData() != null) {
            // 이미지 데이터를 Base64 인코딩하여 문자열로 변환하여 전달
            String base64ImageData = Base64.getEncoder().encodeToString(sellerBoardDtoResponse.getImageData());
            sellerBoardDtoResponse.setImageData(base64ImageData.getBytes());
        }

        return ResponseEntity.status(HttpStatus.OK).body(sellerBoardDtoResponse);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/getAllSellerBoard")
    public ResponseEntity<List<SellerBoard>> getAllSellerBoard(HttpServletRequest request)
    {
        List<SellerBoard> sellerBoardResponse = sellerBoardService.getAllApplications();
        LOGGER.info("호출 API: " + "get all frontend applications" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(sellerBoardResponse);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SellerBoardDto> updateSellerBoard(
            @PathVariable Long id,
            @RequestPart(required = false) MultipartFile imageFile,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) Long price,
            @RequestParam(required = false) String keyword
    ) throws Exception {
        SellerBoardDto sellerBoardDto = SellerBoardDto.builder()
                .id(id)
                .name(name)
                .product(product)
                .price(price)
                .keyword(keyword)
                .build();
        if (imageFile != null) {
            Image image = imageService.uploadImage(imageFile);
            sellerBoardDto.setImage(image);
        }
        SellerBoardDto sellerBoardDtoResponse = sellerBoardService.updateSellerBoard(id, sellerBoardDto);
        return ResponseEntity.status(HttpStatus.OK).body(sellerBoardDtoResponse);
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping()
    public ResponseEntity<String> deleteSellerBoard (Long id, HttpServletRequest request) throws Exception
    {
        sellerBoardService.deleteSellerBoard(id);

        LOGGER.info("호출 API: " + "delete SellerBoard" + " 접속자 IP: " + request.getRemoteAddr() + ", 최초 접속 시간: " +  LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body("성공적으로 삭제 되었습니다.");
    }
}
