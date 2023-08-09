package com.springboot.farm2marketbackend.data.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {
    private String name;
    private String imgType;
    private byte[] imageData;
    private Long supplier_id;
    private Long seller_id;

}