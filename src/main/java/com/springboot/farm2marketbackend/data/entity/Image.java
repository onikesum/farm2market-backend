package com.springboot.farm2marketbackend.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "name")
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;
    private String imgType;

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
