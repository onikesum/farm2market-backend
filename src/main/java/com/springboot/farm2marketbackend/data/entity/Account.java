package com.springboot.farm2marketbackend.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@ToString(exclude = "name")
@Table(name = "account")
// 다른 패키지에서 생성자 함부로 생성하지 마세요!
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id;

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

    @Builder
    public Account(Long id, String username, String password, String email, String age, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
    }
}