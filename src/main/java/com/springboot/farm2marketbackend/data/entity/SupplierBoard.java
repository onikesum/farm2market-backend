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
@Table(name = "supplierBoard")
public class SupplierBoard {
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

    @Column(nullable = false)
    private Long supplier_id;
    @Lob
    @Column
    private byte[] imageData; // 이미지 데이터

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image")
    private Image image;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}

