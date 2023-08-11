package com.springboot.farm2marketbackend.data.entity;

import com.springboot.farm2marketbackend.service.SellerBoardService;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;
    private String imgType;
    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
    private SupplierBoard supplierBoard;
    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
    private SellerBoard sellerBoard;
    @Lob
    @Column
    private byte[] imageData;

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    @Builder
    public Image(String name, String imgType, byte[] imageData) {
        this.name = name;
        this.imgType = imgType;
        this.imageData = imageData;
    }

}
