package com.springboot.farm2marketbackend.data.dto;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SupplierBoardDto {
    private Long id;

//    private MultipartFile imgFile; //브라우저에서 controller 파일 담는 용도
//    private String originalFileName; //원본 파일 이름
//    private String storedFileName; // 서버 저장용 이름
//    private int fileAttached; // 파일 첨부 여부(첨부1, 미첨부 0)


    private String name;

    private String product;

    private Long price;

    private String keyword;

    private Long supplier_id;
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
