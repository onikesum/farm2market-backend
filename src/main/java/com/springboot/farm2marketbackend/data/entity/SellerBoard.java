package com.springboot.farm2marketbackend.data.entity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(exclude = "name")
@Table(name = "sellerBoard")
public class SellerBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String keyword;

    @Lob
    @Column
    private byte[] imageData; // 이미지 데이터

    @Column(name = "image_id") // image_id 컬럼 추가
    private Long imageId; // 이미지와의 연결을 위한 외래키

    @OneToOne
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private Image image;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

}
