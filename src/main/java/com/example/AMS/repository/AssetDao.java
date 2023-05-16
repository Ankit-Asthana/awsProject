package com.example.AMS.repository;

import com.example.AMS.entity.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetDao  extends JpaRepository<Assets,Long> {
//    Optional<ImageData> findByName(String fileName);
}