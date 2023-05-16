package com.example.AMS.service;

import com.example.AMS.entity.AssetAssignment;
import com.example.AMS.repository.AssetAssignmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AssetAssignmentService {
    @Autowired
    private AssetAssignmentDao assetAssignmentDao;

    public AssetAssignment newAssignment(AssetAssignment assetAssignment) {
        assetAssignment.setIssuedDate(new Date());
        return assetAssignmentDao.save(assetAssignment);
    }

    public List<AssetAssignment> getAllAssignment() {
        return assetAssignmentDao.findAll();
    }

    public AssetAssignment getAssignmentById(Long assignId) {
        return assetAssignmentDao.findById(assignId).orElse(null);
    }

    public void deleteAssignment(Long id) {
         assetAssignmentDao.deleteById(id);
    }
}
