package com.example.AMS.repository;

import com.example.AMS.entity.AssetTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTypeDao extends JpaRepository<AssetTypes,Long> {
}
