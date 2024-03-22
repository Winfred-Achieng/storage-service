package com.winfred.multipartfile.repository;

import com.winfred.multipartfile.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData , Integer> {

    Optional<ImageData> findImageByName(String fileName);
}
