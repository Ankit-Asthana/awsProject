package com.example.AMS.repository;

import com.example.AMS.entity.AssetTransferHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTransferHistoryDao extends JpaRepository<AssetTransferHistory,Long> {
}
