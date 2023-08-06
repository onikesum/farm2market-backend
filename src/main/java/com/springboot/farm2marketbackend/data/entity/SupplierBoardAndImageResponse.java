package com.springboot.farm2marketbackend.data.entity;

import com.springboot.farm2marketbackend.data.dto.SupplierBoardDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class SupplierBoardAndImageResponse {
    private SupplierBoardDto supplierBoard;
    private MultipartFile imageFile;

}