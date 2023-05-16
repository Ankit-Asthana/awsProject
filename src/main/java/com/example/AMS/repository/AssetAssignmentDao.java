package com.example.AMS.repository;


import com.example.AMS.entity.AssetAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetAssignmentDao extends JpaRepository<AssetAssignment,Long> {
}