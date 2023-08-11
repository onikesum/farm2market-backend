package com.springboot.farm2marketbackend.data.dto;

import com.springboot.farm2marketbackend.data.entity.Image;
import com.springboot.farm2marketbackend.data.entity.SellerBoard;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SellerBoardDto {
    private Long id;

    private String name;

    private String product;

    private Long price;

    private String keyword;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
    private Image image;
    private Long imageId;

    private ImageDto imageDto;
    private String imageName; // 이미지 파일명
    private String imageType; // 이미지 파일 타입
    private byte[] imageData;

    public SellerBoard toEntity() {
        return SellerBoard.builder()
                .id(id)
                .name(name)
                .product(product)
                .price(price)
                .keyword(keyword)
                .imageId(imageId)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
    }
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
}
