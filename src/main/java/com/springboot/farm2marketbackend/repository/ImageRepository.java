package com.springboot.farm2marketbackend.repository;

import com.springboot.farm2marketbackend.data.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String fileName);
}
